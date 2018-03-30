package com.hewei.nestedscrollviewtest;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.FrameLayout;

/**
 * Created by fengyinpeng on 2018/3/23.
 */

public class BasicNestTestActivity extends AppCompatActivity {
    private static final String TAG = "Activity";

    private static int mTouchSlop;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ContainerView containerView = new ContainerView(this);
        containerView.setBackgroundColor(Color.BLUE);
        ChildView childView = new ChildView(this);
        childView.setBackgroundColor(Color.RED);
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(300, 500);
        lp.gravity = Gravity.CENTER;
        childView.setLayoutParams(lp);
        containerView.addView(childView);
        setContentView(containerView);

        final ViewConfiguration configuration = ViewConfiguration.get(this);
        mTouchSlop = configuration.getScaledTouchSlop();
    }

    public static class ContainerView extends FrameLayout {
        private static final String TAG = "Container";

        private float mStartX;
        private View mChildView;

        public ContainerView(Context context) {
            super(context);
        }

        @Override
        public boolean onInterceptTouchEvent(MotionEvent ev) {
            boolean ret = false;
            final int action = ev.getAction();
            if (action == MotionEvent.ACTION_DOWN) {
                mStartX = ev.getX();
            } else if (action == MotionEvent.ACTION_MOVE) {
                float deta = Math.abs(ev.getX() - mStartX);
                if (deta > BasicNestTestActivity.mTouchSlop) {
                    ret = true;
                }
            }

            Log.d(TAG, "onInterceptTouchEvent ret = " + ret + ", event = " + ev);
            return ret;
        }

        @Override
        public boolean onTouchEvent(MotionEvent ev) {
            boolean ret = false;
            final int action = ev.getAction();
            if (action == MotionEvent.ACTION_DOWN) {
                ret = true;
            } else if (action == MotionEvent.ACTION_MOVE) {
                float deta = ev.getX() - mStartX;

                if (mChildView == null) {
                    mChildView = getChildAt(0);
                }
                if (mChildView != null) {
                    mChildView.setTranslationX(deta);
                }
                ret = true;
            }

            Log.d(TAG, "onTouchEvent ret = " + ret + ", event = " + ev);
            return ret;
        }
    }

    public static class ChildView extends FrameLayout {
        private static final String TAG = "Child";

        public ChildView(Context context) {
            super(context);
        }

        @Override
        public boolean onInterceptTouchEvent(MotionEvent ev) {
            boolean ret = super.onInterceptTouchEvent(ev);
            Log.d(TAG, "onInterceptTouchEvent ret = " + ret + ", event = " + ev);
            return ret;
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            boolean ret = super.onTouchEvent(event);
            final int action = event.getAction();

            if (action == MotionEvent.ACTION_DOWN) {
                ret = true;
            }

            Log.d(TAG, "onTouchEvent ret = " + ret + ", event = " + event);
            return ret;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean ret = super.onTouchEvent(event);
        Log.d(TAG, "onTouchEvent ret = " + ret + ", event = " + event);
        return ret;
    }
}
