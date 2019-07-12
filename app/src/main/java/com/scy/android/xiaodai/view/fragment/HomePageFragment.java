package com.scy.android.xiaodai.view.fragment;

import android.Manifest;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.scy.android.xiaodai.R;
import com.scy.android.xiaodai.app.MainApplication;
import com.scy.android.xiaodai.common.Constants;
import com.scy.android.xiaodai.common.arcsoft.DetecterActivity;
import com.scy.android.xiaodai.common.arcsoft.RegisterActivity;
import com.scy.android.xiaodai.utils.GlideImageLoader;
import com.scy.android.xiaodai.utils.ToastHelper;
import com.scy.android.xiaodai.view.activity.WebVIewActivity;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.yzq.zxinglibrary.android.CaptureActivity;
import com.yzq.zxinglibrary.bean.ZxingConfig;
import com.yzq.zxinglibrary.common.Constant;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.app.Activity.RESULT_OK;
import static com.scy.android.xiaodai.common.Constants.REQUEST_CODE_SCAN;
import static com.yzq.zxinglibrary.decode.ImageUtil.isDownloadsDocument;
import static com.yzq.zxinglibrary.decode.ImageUtil.isExternalStorageDocument;
import static com.yzq.zxinglibrary.decode.ImageUtil.isMediaDocument;

/**
 * created by scy on 2019/6/29 09:45
 * gmail：cherseey@gmail.com
 */
public class HomePageFragment extends BaseFragment {

    private final String TAG = this.getClass().toString();
    //基本属性

    @BindView(R.id.homepage_swipe_refresh)
    SwipeRefreshLayout homepageSwipeRefresh;
    @BindView(R.id.addscan)
    ImageView addscan;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.consume_pie_chart)
    PieChart pieChart;
    private List<Integer> pictureList = new ArrayList<>();


    @Override
    public int initView() {
        return R.layout.fragment_page_home;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBannerdata();
    }

    private void initPieChart() {
        List<PieEntry> strings = new ArrayList<>();
        strings.add(new PieEntry(7.8f,"收益"));
        strings.add(new PieEntry(82.2f,"投入"));
        PieDataSet dataSet = new PieDataSet(strings,"年化收益率");
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(getResources().getColor(R.color.pie));
        colors.add(getResources().getColor(R.color.pie3));
        dataSet.setColors(colors);
        PieData pieData = new PieData(dataSet);
        pieData.setDrawValues(true);
        Description description = new Description();
        description.setText("历史");
        pieChart.setCenterText("7.8%");
        pieChart.setRotationAngle(90);
        pieChart.setUsePercentValues(true);
        pieChart.animateXY(1000, 1000);
        pieChart.setData(pieData);
        pieChart.setDescription(description);
        pieChart.invalidate();
    }

    @Override
    public void initViewControll() {
        //设置下拉刷新
        homepageSwipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        homepageSwipeRefresh.setProgressViewEndTarget(false, 300);
        homepageSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        homepageSwipeRefresh.setRefreshing(false);
                    }
                }, 2000);
            }
        });
        addscan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        initPieChart();
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        banner.setIndicatorGravity(BannerConfig.RIGHT);
        banner.setImageLoader(new GlideImageLoader());
        banner.setImages(pictureList);
        banner.setBannerAnimation(Transformer.Accordion);
        banner.setIndicatorGravity(BannerConfig.CENTER);
        banner.setDelayTime(5000);
        banner.start();
    }

    @Override
    public void initData() {

    }

    private void initBannerdata() {
        pictureList.add(R.mipmap.banner_1);
        pictureList.add(R.mipmap.banner_2);
        pictureList.add(R.mipmap.banner_3);
    }



    @Override
    public void onStart() {
        super.onStart();
        banner.startAutoPlay();
    }

    @Override
    public void onResume() {
        super.onResume();
        //initToolbar();
    }

    @Override
    public void onStop() {
        super.onStop();
        banner.stopAutoPlay();
    }



}
