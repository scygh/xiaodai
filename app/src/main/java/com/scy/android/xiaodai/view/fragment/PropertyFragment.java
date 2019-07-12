package com.scy.android.xiaodai.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.scy.android.xiaodai.R;
import com.scy.android.xiaodai.adapter.MyExpandListViewAdapter;
import com.scy.android.xiaodai.bean.Elbdetail;
import com.scy.android.xiaodai.bean.ExpandListViewBean;
import com.scy.android.xiaodai.view.fragment.propertyFragments.propertydetailFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.relex.circleindicator.CircleIndicator;

/**
 * created by scy on 2019/6/29 09:45
 * gmail：cherseey@gmail.com
 */
public class PropertyFragment extends BaseFragment {

    @BindView(R.id.property_viewpager)
    ViewPager propertyViewpager;
    @BindView(R.id.indicator)
    CircleIndicator indicator;
    @BindView(R.id.expendablelistview)
    ExpandableListView expendablelistview;
    private List<ExpandListViewBean> groupList;
    private List<Elbdetail> mElbdetails;
    private List<List<Elbdetail>> childList;
    private MyExpandListViewAdapter adapter;

    @Override
    public int initView() {
        return R.layout.fragment_property;
    }

    @Override
    public void initViewControll() {
        propertyViewpager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return new propertydetailFragment();
            }

            @Override
            public int getCount() {
                return 4;
            }
        });
        indicator.setViewPager(propertyViewpager);
        groupList = new ArrayList<>();
        childList = new ArrayList<>();
        mElbdetails = new ArrayList<>();
        mElbdetails.add(new Elbdetail("自动投标工具","0.00元"));
        mElbdetails.add(new Elbdetail("友金e富","0.00元"));
        childList.add(new ArrayList<Elbdetail>());
        childList.add(mElbdetails);
        childList.add(new ArrayList<Elbdetail>());
        groupList.add(new ExpandListViewBean(R.drawable.zhye,"账户余额","0.00元"));
        groupList.add(new ExpandListViewBean(R.drawable.wd,"网贷","0.00元"));
        groupList.add(new ExpandListViewBean(R.drawable.bx,"保险","0份保单"));
        adapter = new MyExpandListViewAdapter(groupList,childList,mContext);
        expendablelistview.setAdapter(adapter);
        expendablelistview.setGroupIndicator(null);
    }

    @Override
    public void initData() {
        super.initData();
    }


}
