package com.example.stickinessview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    private LinearLayout linearLayout;
    private StickinessView stickinessView;
    private static final float TOUCH_MOVE_Y = 600;
    private float mTouchMoveStartY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linearLayout = (LinearLayout) findViewById(R.id.activity_main);
        stickinessView = (StickinessView) findViewById(R.id.stickiness_view);
        linearLayout.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (v.getId()) {
            case R.id.activity_main:
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        mTouchMoveStartY = event.getY();
                        return true;
                    case MotionEvent.ACTION_MOVE:
                        float y = event.getY();
                        if (y >= mTouchMoveStartY) {//往下拉
                            float move_distance = y - mTouchMoveStartY;
                            float progress = move_distance >= TOUCH_MOVE_Y ? 1 : move_distance / TOUCH_MOVE_Y;
                            stickinessView.setProgress(progress);
                        }
                        return true;
                    default:
                        break;
                }
                break;
        }
        return false;
    }
}
