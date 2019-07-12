package com.scy.android.xiaodai.utils;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 沈程阳
 * created by scy on 2019/5/15 14:47
 * 邮箱：1797484636@qq.com
 */
public class PermissionUtil {

    public static void requestNeedPermission(final Activity context, String[] permissions, int requestcode){
        boolean deni = false;
        List<String> permissionlist = new ArrayList<>();
        for (int i = 0; i < permissions.length; i++) {
            if (ContextCompat.checkSelfPermission(context, permissions[i]) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(context,permissions[i])) {
                    //用户拒绝了权限
                    deni = true;
                } else {
                    permissionlist.add(permissions[i]);
                }
            }
        }
        if (deni) {
            showDialog(context);
        }
        if (permissionlist.size() > 0) {
                ActivityCompat.requestPermissions(context, permissionlist.toArray(new String[permissionlist.size()]), requestcode);
        } else {

        }
    }

    private static void showDialog(final Context context) {
        AlertDialog dialog = new AlertDialog.Builder(context)
                .setTitle("提示")
                .setMessage("你拒绝了一些必要的权限，请手动前往设置界面打开权限")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        gotoHuaweiPermission(context);
                    }
                }).create();
        dialog.show();
        TextView tvMsg = (TextView) dialog.findViewById(android.R.id.message);
        tvMsg.setTextSize(14);
        tvMsg.setTextColor(Color.parseColor("#4E4E4E"));
        dialog.getButton(dialog.BUTTON_NEGATIVE).setTextSize(14);
        dialog.getButton(dialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#8C8C8C"));
        dialog.getButton(dialog.BUTTON_POSITIVE).setTextSize(14);
        dialog.getButton(dialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#1DA6DD"));
        try {
            Field mAlert = AlertDialog.class.getDeclaredField("mAlert");
            mAlert.setAccessible(true);
            Object alertController = mAlert.get(dialog);
            Field mTitleView = alertController.getClass().getDeclaredField("mTitleView");
            mTitleView.setAccessible(true);
            TextView tvTitle = (TextView) mTitleView.get(alertController);
            if (null != tvTitle) {
                tvTitle.setTextSize(16);
                tvTitle.setTextColor(Color.parseColor("#000000"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void gotoHuaweiPermission(Context context) {
        try {
            Intent intent = new Intent();
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            ComponentName comp = new ComponentName("com.huawei.systemmanager", "com.huawei.permissionmanager.ui.MainActivity");//华为权限管理
            intent.setComponent(comp);
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            context.startActivity(getAppDetailSettingIntent(context));
        }

    }

    private static Intent getAppDetailSettingIntent(Context context) {
        Intent localIntent = new Intent();
        localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= 9) {
            localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            localIntent.setData(Uri.fromParts("package", context.getPackageName(), null));
        } else if (Build.VERSION.SDK_INT <= 8) {
            localIntent.setAction(Intent.ACTION_VIEW);
            localIntent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
            localIntent.putExtra("com.android.settings.ApplicationPkgName", context.getPackageName());
        }
        return localIntent;
    }

}
