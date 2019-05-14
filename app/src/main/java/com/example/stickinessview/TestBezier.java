package com.example.stickinessview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

public class TestBezier extends View {

    private Paint mPaint;
    private Path mPath;

    public TestBezier(Context context) {
        super(context);
        init();
    }

    public TestBezier(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);
        //一阶
        mPath = new Path();
        mPath.moveTo(100, 100);
        mPath.lineTo(300, 300);

        //二阶
        //mPath.quadTo(400,0,500,300);
        //相对实现
        mPath.rQuadTo(100,-300,200,0);

        //三阶
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(mPath, mPaint);
    }
}
