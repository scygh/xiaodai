package com.scy.android.xiaodai.view.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.scy.android.xiaodai.R;
import com.scy.android.xiaodai.adapter.MainViewpagerAdapter;
import com.scy.android.xiaodai.utils.ToastHelper;
import com.scy.android.xiaodai.view.fragment.BaseFragment;
import com.scy.android.xiaodai.view.fragment.HomePageFragment;
import com.scy.android.xiaodai.view.fragment.MoreFragment;
import com.scy.android.xiaodai.view.fragment.MyinvestFragment;
import com.scy.android.xiaodai.view.fragment.NoScrollViewpager;
import com.scy.android.xiaodai.view.fragment.PropertyFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.main_viewpager)
    NoScrollViewpager mainViewpager;
    @BindView(R.id.main_radiogroup)
    RadioGroup mainRadiogroup;
    @BindView(R.id.radio_home)
    RadioButton radioHome;
    @BindView(R.id.radio_news)
    RadioButton radioNews;
    @BindView(R.id.radio_service)
    RadioButton radioService;
    @BindView(R.id.radio_gov)
    RadioButton radioGov;
    private List<BaseFragment> mFragments;
    public static FragmentManager sFragmentManager;

    @Override
    public int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        mFragments = new ArrayList<>();
        mFragments.add(new HomePageFragment());
        mFragments.add(new MyinvestFragment());
        mFragments.add(new PropertyFragment());
        mFragments.add(new MoreFragment());
        sFragmentManager = getSupportFragmentManager();
        mainViewpager.setAdapter(new MainViewpagerAdapter(sFragmentManager, mFragments));
        mainViewpager.setCanScroll(false);
    }

    @OnClick({R.id.radio_home, R.id.radio_news, R.id.radio_service, R.id.radio_gov})
    public void radioButttonOnClick(View view) {
        switch (view.getId()) {
            case R.id.radio_home:
                mainViewpager.setCurrentItem(0, false);
                break;
            case R.id.radio_news:
                mainViewpager.setCurrentItem(1, false);
                break;
            case R.id.radio_service:
                mainViewpager.setCurrentItem(2, false);
                break;
            case R.id.radio_gov:
                mainViewpager.setCurrentItem(3, false);
                break;
            default:
                break;
        }
    }

    private long firstTime = 0;

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                long secondTime = System.currentTimeMillis();
                if (secondTime - firstTime > 2000) {
                    ToastHelper.showToast("再按一次退出");
                    firstTime = secondTime;
                } else {
                    finish();
                }
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ToastHelper.cancelToast();
    }
}
