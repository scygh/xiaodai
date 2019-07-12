package com.scy.android.xiaodai.view.fragment.myinvestFragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scy.android.xiaodai.R;
import com.scy.android.xiaodai.adapter.WdRecyclerViewAdapter;
import com.scy.android.xiaodai.view.fragment.BaseFragment;
import com.sunfusheng.marqueeview.MarqueeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * created by scy on 2019/6/29 09:45
 * gmail：cherseey@gmail.com
 */
public class WdFragment extends BaseFragment {

    @BindView(R.id.marqueeView)
    MarqueeView marqueeView;
    @BindView(R.id.wdrecyclerview)
    RecyclerView wdrecyclerview;
    @BindView(R.id.wdrefresh)
    SwipeRefreshLayout mSwipeRefreshLayout;
    List<String> messages = new ArrayList<>();

    @Override
    public int initView() {
        return R.layout.fragment_wd;
    }

    @Override
    public void initViewControll() {
        messages.add("用户历史收益均已兑付");
        messages.add("全面升级资金存管系统");
        marqueeView.startWithList(messages);
        wdrecyclerview.setLayoutManager(new LinearLayoutManager(mContext));
        wdrecyclerview.setAdapter(new WdRecyclerViewAdapter(mContext));
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        mSwipeRefreshLayout.setProgressViewEndTarget(false, 300);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                }, 2000);
            }
        });
    }

    @Override
    public void initData() {
        super.initData();
    }

    @Override
    public void onStart() {
        super.onStart();
        marqueeView.startFlipping();
    }

    @Override
    public void onStop() {
        super.onStop();
        marqueeView.stopFlipping();
    }
}
