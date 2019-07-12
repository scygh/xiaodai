package com.scy.android.xiaodai.view.fragment.myinvestFragments;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scy.android.xiaodai.R;
import com.scy.android.xiaodai.adapter.MainViewpagerAdapter;
import com.scy.android.xiaodai.view.activity.MainActivity;
import com.scy.android.xiaodai.view.fragment.BaseFragment;
import com.scy.android.xiaodai.view.fragment.NoScrollViewpager;
import com.scy.android.xiaodai.view.fragment.myinvestFragments.bxFragments.firstFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * created by scy on 2019/6/29 09:45
 * gmail：cherseey@gmail.com
 */
public class BxFragment extends BaseFragment {

    @BindView(R.id.bx_tablayout)
    TabLayout bxTablayout;
    @BindView(R.id.bx_viewpager)
    NoScrollViewpager bxViewpager;
    private String[] arr;
    @Override
    public int initView() {
        return R.layout.fragment_bx;
    }

    @Override
    public void initViewControll() {
        arr = getResources().getStringArray(R.array.bxtab);
        for(int i = 0;i<arr.length;i++) {
            bxTablayout.addTab(bxTablayout.newTab());
        }
        bxTablayout.setupWithViewPager(bxViewpager,false);
        bxViewpager.setAdapter(new FragmentPagerAdapter(this.getChildFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return new firstFragment();
            }

            @Override
            public int getCount() {
                return arr.length;
            }

        });
        for (int i = 0; i< arr.length; i++) {
            bxTablayout.getTabAt(i).setText(arr[i]);
        }
    }

    @Override
    public void initData() {
        super.initData();
    }


}
