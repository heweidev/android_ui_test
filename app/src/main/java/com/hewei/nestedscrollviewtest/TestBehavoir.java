package com.hewei.nestedscrollviewtest;

import android.content.Context;
import android.graphics.Rect;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by fengyinpeng on 2018/3/26.
 */

public class TestBehavoir extends CoordinatorLayout.Behavior<View> {

    public TestBehavoir(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //当 dependency instanceof AppBarLayout 返回TRUE，将会调用onDependentViewChanged（）方法
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency instanceof AppBarLayout;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        //根据dependency top值的变化改变 child 的 translationY

        int height = dependency.getHeight();

        Rect rect = new Rect();
        boolean ret = dependency.getLocalVisibleRect(rect);
        if (ret) {
            final int TAB_HEIGHT = 100;
            float alpha = 1.0f * (height - rect.height()) / height;
            child.setAlpha(alpha);

            if (child.getVisibility() == View.GONE) {
                child.setVisibility(View.VISIBLE);
            } else if (alpha < 0.1 && child.getVisibility() == View.VISIBLE) {
                child.setVisibility(View.GONE);
            }
        } else {
            child.setVisibility(View.VISIBLE);
        }

        return true;
    }
}