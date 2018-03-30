package com.hewei.nestedscrollviewtest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * Created by fengyinpeng on 2018/3/27.
 */

public class LayoutTestAcitivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_test);

        final View line1 = findViewById(R.id.line1);
        final View line2 = findViewById(R.id.line2);

        final MyView testView = new MyView(this);
        testView.setBackgroundColor(Color.RED);
        testView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 100));
        ((ViewGroup) findViewById(R.id.viewRoot)).addView(testView);

        line1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LayoutTestAcitivity.this, "Line1", Toast.LENGTH_SHORT).show();
                testView.requestLayout();
                //testView.invalidate();
            }
        });

        findViewById(R.id.btnAction).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                final LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) line1.getLayoutParams();
                ValueAnimator animator = ValueAnimator.ofInt(lp.height, lp.height - 20);
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        lp.height = (int) animation.getAnimatedValue();
                        line1.setLayoutParams(lp);
                    }
                });
                animator.start();
                */

                //ViewCompat.offsetTopAndBottom(line2, -50);
                float scaleX = line2.getScaleX();
                line2.setScaleX(0.8f * scaleX);
                line2.setScaleY(0.8f * scaleX);

                /*
                v.animate().translationX(200).setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        int a = 40;
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                        int a = 100;
                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                }).start();
                */
            }
        });
    }

    public static class MyView extends View {
        private static final String TAG = "MyView";

        public MyView(Context context) {
            super(context);
        }

        @Override
        protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
            super.onLayout(changed, left, top, right, bottom);
            Log.d(TAG, "onLayout");
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            Log.d(TAG, "onMeasure");
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            Log.d(TAG, "onDraw");
        }
    }
}
