package com.hewei.nestedscrollviewtest.ani;

import android.animation.Animator;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.hewei.nestedscrollviewtest.R;

public class AnimationTestActivity extends AppCompatActivity {
    private TextView mSwitcherTextView;
    private int mCurIndex;

    String[] textArray = new String[] {
            "上证指数", "深城指数"
    };

    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_test);

        mSwitcherTextView = (TextView) findViewById(R.id.textSwitch);
        initSwitcher();

        findViewById(R.id.btnTest).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //up2();
                scale();
            }
        });
    }

    private void initSwitcher() {
        mCurIndex = 0;
        mSwitcherTextView.setText(textArray[mCurIndex]);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //mHandler.postDelayed(mAniRunnable, 2500);
    }

    private void up1() {
        mSwitcherTextView.animate().translationY(-75).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mSwitcherTextView.setTranslationY(75);
                //mSwitcherTextView.setAlpha(0);
                mCurIndex = (mCurIndex + 1) % 2;
                mSwitcherTextView.setText(textArray[mCurIndex]);
                mSwitcherTextView.animate().translationY(0).setListener(null).start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        }).start();
    }

    private Runnable mAniRunnable = new Runnable() {
        @Override
        public void run() {
            up2();
            mHandler.postDelayed(mAniRunnable, 2500);
        }
    };

    private void scale() {
        // scale动画需要设置parent的clipchildren属性
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.text_scale);
        animation.setDuration(1000);
        mSwitcherTextView.startAnimation(animation);
    }

    private void up2() {
        final Animation enter = AnimationUtils.loadAnimation(this, R.anim.text_switch_enter);
        Animation exit = AnimationUtils.loadAnimation(this, R.anim.text_switch_exit);

        exit.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mSwitcherTextView.startAnimation(enter);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        mSwitcherTextView.startAnimation(exit);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mHandler.removeCallbacks(mAniRunnable);
    }
}
