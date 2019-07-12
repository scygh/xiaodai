package com.scy.android.xiaodai.view.fragment.myinvestFragments.bxFragments;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.scy.android.xiaodai.R;
import com.scy.android.xiaodai.adapter.BxRecyclerViewAdapter;
import com.scy.android.xiaodai.view.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * created by scy on 2019/6/29 09:45
 * gmail：cherseey@gmail.com
 */
public class firstFragment extends BaseFragment {

    @BindView(R.id.bxrecyclerview)
    RecyclerView bxrecyclerview;
    @BindView(R.id.bxrefresh)
    SwipeRefreshLayout bxrefresh;
    List<Integer> pictures = new ArrayList<>();

    @Override
    public int initView() {
        return R.layout.fragment_first;
    }

    @Override
    public void initViewControll() {
        pictures.clear();
        pictures.add(R.drawable.bx_1);
        pictures.add(R.drawable.bx_2);
        pictures.add(R.drawable.bx_3);
        pictures.add(R.drawable.bx_4);
        bxrecyclerview.setLayoutManager(new LinearLayoutManager(mContext));
        bxrecyclerview.setAdapter(new BxRecyclerViewAdapter(mContext,pictures));
        bxrecyclerview.addItemDecoration(new DividerItemDecoration(mContext,DividerItemDecoration.VERTICAL));
        bxrefresh.setColorSchemeResources(R.color.colorPrimary);
        bxrefresh.setProgressViewEndTarget(false, 300);
        bxrefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        bxrefresh.setRefreshing(false);
                    }
                }, 2000);
            }
        });
    }

    @Override
    public void initData() {
        super.initData();
    }

}
