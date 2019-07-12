package com.scy.android.xiaodai.view.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;

import com.scy.android.xiaodai.R;
import com.scy.android.xiaodai.adapter.MainViewpagerAdapter;
import com.scy.android.xiaodai.view.activity.MainActivity;
import com.scy.android.xiaodai.view.fragment.myinvestFragments.BxFragment;
import com.scy.android.xiaodai.view.fragment.myinvestFragments.WdFragment;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * created by scy on 2019/6/29 09:45
 * gmail：cherseey@gmail.com
 */
public class MyinvestFragment extends BaseFragment {

    @BindView(R.id.invest_tablayout)
    TabLayout investTablayout;
    @BindView(R.id.invest_viewpager)
    NoScrollViewpager investViewpager;
    private MainViewpagerAdapter mMainViewpagerAdapter;
    private ArrayList<BaseFragment> mFragments = new ArrayList<>();
    @Override
    public int initView() {
        return R.layout.fragment_myinvest;
    }

    @Override
    public void initViewControll() {
        mFragments.clear();
        String[] arr = getResources().getStringArray(R.array.tab);
        mFragments.add(new WdFragment());
        mFragments.add(new BxFragment());
        investTablayout.removeAllTabs();
        for (int i = 0; i< arr.length; i++) {
            investTablayout.addTab(investTablayout.newTab());
        }
        investTablayout.setupWithViewPager(investViewpager, false);
        mMainViewpagerAdapter = new MainViewpagerAdapter(this.getChildFragmentManager(),mFragments);
        investViewpager.setAdapter(mMainViewpagerAdapter);
        investViewpager.setCanScroll(false);
        for (int i = 0; i< arr.length; i++) {
            investTablayout.getTabAt(i).setText(arr[i]);
        }

    }

    @Override
    public void initData() {
        super.initData();
    }

}
