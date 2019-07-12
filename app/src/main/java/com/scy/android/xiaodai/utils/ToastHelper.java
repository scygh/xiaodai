package com.scy.android.xiaodai.utils;

import android.widget.Toast;
import com.scy.android.xiaodai.app.MainApplication;

/**
 * 沈程阳
 * created by scy on 2019/4/3 14:44
 * 邮箱：1797484636@qq.com
 */
public class ToastHelper {

    public static Toast mToast = null;
    /**
     * 弹出Toast
     * @param text    提示的文本
     */
    public static void showToast(String text) {
            if (mToast == null) {
                mToast = Toast.makeText(MainApplication.getMainContext(),text,Toast.LENGTH_SHORT);
            } else {
                mToast.setText(text);
                mToast.setDuration(Toast.LENGTH_SHORT);
            }
            mToast.show();
    }

    /**
     *
     * 弹出Toast
     * @param text    提示的文本
     * @param gravity  位置（Gravity.CENTER;Gravity.TOP;...）
     */
    public static void showToast(String text,int gravity) {
        if (mToast == null) {
            mToast = Toast.makeText(MainApplication.getMainContext(), text, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(text);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.setGravity(gravity, 0, 0);
        mToast.show();
    }

    /**
     * 关闭Toast
     */
    public static void cancelToast(){
        if(mToast !=null){
            mToast.cancel();
        }
    }
}
