package com.hewei.nestedscrollviewtest;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by fengyinpeng on 2018/3/29.
 * 实现一个简单的控制，标题栏高度不变。listview向上滑动会覆盖header，覆盖一部分后停止覆盖，listview开始向上滑动。
 * 此时向下滑动，listview覆盖header部分减少，直到不覆盖。listview自己开始向下滑动
 */

public class DumyBehavior extends CoordinatorLayout.Behavior<View> {
    private static final String TAG = "DumyBehavior";

    public DumyBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
        return true;
    }

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dx, int dy, int[] consumed) {
        int top = target.getTop();
        Log.d(TAG, "dy = " + dy + ", top = " + top);

        int newDeta = fixDeta(dy, top);
        if (newDeta != 0) {
            ViewCompat.offsetTopAndBottom(target, -newDeta);
        }
        consumed[1] = -newDeta;
    }

    private int fixDeta(int deta, int top) {
        if (top - deta > 500) {
            deta = top - 500;
        } else if (top - deta < 300) {
            deta = top - 300;
        }

        return deta;
    }

    @Override
    public boolean onLayoutChild(CoordinatorLayout parent, View child, int layoutDirection) {
        /*
        这个地方是关键，RecyclerView的大小在这里计算。 view的大小 = 屏幕尺寸 - 头部最小高度
         */
        child.layout(0, 500, 1080, 1980);
        return true;
    }
}
