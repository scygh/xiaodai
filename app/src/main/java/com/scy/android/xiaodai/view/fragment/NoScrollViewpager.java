package com.scy.android.xiaodai.view.fragment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * created by scy on 2019/7/2 08:30
 * gmail：cherseey@gmail.com
 */
public class NoScrollViewpager extends ViewPager {

    public boolean isCanScroll = true;

    public NoScrollViewpager(@NonNull Context context) {
        super(context);
    }

    public NoScrollViewpager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void setCanScroll(boolean canScroll) {
        isCanScroll = canScroll;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (!isCanScroll) {
            return false;
        }
        return super.onTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (!isCanScroll) {
            return false;
        }
        return super.onInterceptTouchEvent(ev);
    }
}
