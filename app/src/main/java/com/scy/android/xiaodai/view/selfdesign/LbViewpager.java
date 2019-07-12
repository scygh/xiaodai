package com.scy.android.xiaodai.view.selfdesign;

import android.content.Context;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.MotionEvent;

/**
 * created by scy on 2019/7/3 09:41
 * gmail：cherseey@gmail.com
 */
public class LbViewpager extends ViewPager {

    private int startX;
    private int startY;


    public LbViewpager(@NonNull Context context) {
        super(context);
    }

    public LbViewpager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    /*@Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        getParent().requestDisallowInterceptTouchEvent(true);
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = (int)getX();
                startY = (int)getY();
                break;
            case MotionEvent.ACTION_MOVE:
                int x = (int)getX();
                int y = (int)getY();
                int dx = x-startX;
                int dy = y-startY;
                //如果是横向滑动的多
                if (Math.abs(dx) > Math.abs(dy)) {
                    int c = getCurrentItem();
                    getParent().requestDisallowInterceptTouchEvent(true);
                } else {
                    //如果是竖直方向滑动的多就让父容器拦截
                    getParent().requestDisallowInterceptTouchEvent(false);
                }
                break;

        }
        return super.dispatchTouchEvent(ev);
    }*/
}
