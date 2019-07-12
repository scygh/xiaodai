package com.scy.android.xiaodai.tasks;

import android.os.Handler;

import com.scy.android.xiaodai.common.Constants;

import java.util.TimerTask;

/**
 * created by scy on 2019/7/3 12:08
 * gmail：cherseey@gmail.com
 */
public class BannerTimerTask extends TimerTask {

    public Handler mHandler;

    public BannerTimerTask(Handler handler) {
        super();
        mHandler = handler;
    }

    @Override
    public void run() {
        mHandler.sendEmptyMessage(Constants.HANDLER_BANNER);
    }
}
