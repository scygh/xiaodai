package com.scy.android.xiaodai.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.scy.android.xiaodai.view.fragment.BaseFragment;

import java.util.List;

/**
 * created by scy on 2019/6/29 09:41
 * gmail：cherseey@gmail.com
 */
public class MainViewpagerAdapter extends FragmentPagerAdapter {

    private List<BaseFragment> mFragments;

    public MainViewpagerAdapter(FragmentManager fm ,List<BaseFragment> cmFragments) {
        super(fm);
        mFragments = cmFragments;
    }

    @Override
    public Fragment getItem(int i) {
        return mFragments.get(i);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
