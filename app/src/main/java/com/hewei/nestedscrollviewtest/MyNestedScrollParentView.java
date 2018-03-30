package com.hewei.nestedscrollviewtest;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.NestedScrollingParentHelper;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by fengyinpeng on 2018/3/23.
 */

public class MyNestedScrollParentView extends LinearLayout implements NestedScrollingParent {
    private NestedScrollingParentHelper mNestedScrollParentHelper;
    private static final String TAG = "MyNestedScroll";

    private View mHeaderView;
    private NestedScrollView mScrollView;
    private float mLastY = 0.0f;
    private int mCurrentOffset;

    public MyNestedScrollParentView(@NonNull Context context) {
        super(context);
        init();
    }

    public MyNestedScrollParentView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyNestedScrollParentView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MyNestedScrollParentView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        mNestedScrollParentHelper = new NestedScrollingParentHelper(this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mHeaderView = findViewById(R.id.headerView);
        mScrollView = (NestedScrollView) findViewById(R.id.scrollView);
    }

    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        Log.d(TAG, "onStartNestedScroll");
        //mInitY = mHeaderView.getTranslationY();
        mLastY = 0.0f;
        return true;
    }

    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        //mHeaderView.setTranslationY(mInitY + dy);

        if (mHeaderView.getHeight() <= 200) {
            consumed[1] = 0;
        } else {
            final float deta = dy - mLastY;
            mLastY = dy;

            if (mHeaderView.getBottom() - deta >= 200) {
                consumed[1] = dy;

                ViewCompat.offsetTopAndBottom(mHeaderView, (int) -deta);
                ViewCompat.offsetTopAndBottom(mScrollView, (int) -deta);
            } else {
                consumed[1] = 0;
            }
        }

        Log.d(TAG, "onNestedPreScroll dy = " + dy);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);


    }

    @Override
    public void onNestedScroll(@NonNull View target, int dxConsumed, int dyConsumed,
                        int dxUnconsumed, int dyUnconsumed) {
        Log.d(TAG, "onNestedScroll dyConsumed = " + dyConsumed + ", dxUnconsumed = " + dxUnconsumed);
    }

    /**
     * Called when a nested scrolling operation initiated by a descendant view is accepted
     * by this ViewGroup.
     *
     * <p>This is a delegate method. Call it from your {@link android.view.ViewGroup ViewGroup}
     * subclass method/{@link android.support.v4.view.NestedScrollingParent} interface method with
     * the same signature to implement the standard policy.</p>
     */
    public void onNestedScrollAccepted(@NonNull View child, @NonNull View target,
                                        int axes) {
        mNestedScrollParentHelper.onNestedScrollAccepted(child, target, axes);
    }

    /**
     * Return the current axes of nested scrolling for this ViewGroup.
     *
     * <p>This is a delegate method. Call it from your {@link android.view.ViewGroup ViewGroup}
     * subclass method/{@link android.support.v4.view.NestedScrollingParent} interface method with
     * the same signature to implement the standard policy.</p>
     */
    public int getNestedScrollAxes() {
        return mNestedScrollParentHelper.getNestedScrollAxes();
    }

    /**
     * React to a nested scroll operation ending.
     *
     * <p>This is a delegate method. Call it from your {@link android.view.ViewGroup ViewGroup}
     * subclass method/{@link android.support.v4.view.NestedScrollingParent} interface method with
     * the same signature to implement the standard policy.</p>
     */
    public void onStopNestedScroll(@NonNull View target) {
        mNestedScrollParentHelper.onStopNestedScroll(target);
        mLastY = 0.0f;
        Log.d(TAG, "onStopNestedScroll 1");
    }
}
