package com.example.stickinessview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class StickinessView extends View {

    private Paint mCirclePaint;
    private int mRadius = 100;
    private int mCirclePointX, mCirclePointY;
    private float mProgress;
    private int mDragHeight = 800;//可拖动的高度

    public StickinessView(Context context) {
        super(context);
        init();
    }

    public StickinessView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mCirclePaint = new Paint();
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setDither(true);//防抖动
        mCirclePaint.setStyle(Paint.Style.FILL);
        mCirclePaint.setColor(Color.BLACK);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //  float x = getWidth() >> 1;
        //float y = getHeight() >> 1;
        canvas.drawCircle(mCirclePointX, mCirclePointY, mRadius, mCirclePaint);
    }

    /**
     * 测量时触发
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
       // super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        int widthMin = 2 * mRadius + getPaddingLeft() + getPaddingRight();
        int heightMin = (int) ((mDragHeight * mProgress + 0.5f) + getPaddingTop() + getPaddingBottom());

        int measureWidth,measureHeight;
        if (widthMode == MeasureSpec.EXACTLY) {
            measureWidth = width;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            measureWidth = Math.min(widthMin, width);
        } else {
            measureWidth = widthMin;
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            measureHeight = height;
        } else if (heightMode == MeasureSpec.AT_MOST) {
            measureHeight = Math.min(heightMin, height);
        } else {
            measureHeight = heightMin;
        }

        setMeasuredDimension(measureWidth,measureHeight);

    }

    /**
     * 当大小改变时触发
     *
     * @param w
     * @param h
     * @param oldw
     * @param oldh
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mCirclePointX = getWidth() >> 1;
        mCirclePointY = getHeight() >> 1;
    }

    public void setProgress(float progress) {
        mProgress = progress;
        //请求重新测量
        requestLayout();

    }
}
