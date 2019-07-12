package com.scy.android.xiaodai.view.activity;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.scy.android.xiaodai.R;
import com.scy.android.xiaodai.bean.ViewBean;

import butterknife.BindView;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class VideoActivity extends BaseActivity {

    @BindView(R.id.jps)
    JCVideoPlayerStandard jzVideo;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.coll)
    CollapsingToolbarLayout coll;

    public static Intent getViewActivityIntent(Context context, ViewBean viewBean) {
        Intent intent = new Intent(context, VideoActivity.class);
        intent.putExtra("video", viewBean);
        return intent;
    }

    @Override
    public int getContentView() {
        return R.layout.activity_video;
    }

    @Override
    public void initView() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        ViewBean bean = getIntent().getParcelableExtra("video");
        jzVideo.setUp(bean.getUrl()
                , JCVideoPlayer.SCREEN_LAYOUT_NORMAL, bean.getTitle());
        Glide.with(this).load(bean.getImg()).into(jzVideo.thumbImageView);
        jzVideo.startVideo();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayerStandard.releaseAllVideos();
    }

    @Override
    public void onBackPressed() {
        if (JCVideoPlayerStandard.backPress()) {
            return;
        }
        super.onBackPressed();
    }


}
