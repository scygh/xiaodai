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
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.scy.android.xiaodai.R;
import com.scy.android.xiaodai.adapter.MoreRecyclerViewAdapter;
import com.scy.android.xiaodai.app.MainApplication;
import com.scy.android.xiaodai.bean.ViewBean;
import com.scy.android.xiaodai.common.Constants;
import com.scy.android.xiaodai.common.arcsoft.DetecterActivity;
import com.scy.android.xiaodai.common.arcsoft.RegisterActivity;
import com.scy.android.xiaodai.utils.ToastHelper;
import com.scy.android.xiaodai.view.activity.VideoActivity;
import com.scy.android.xiaodai.view.activity.WebVIewActivity;
import com.yzq.zxinglibrary.android.CaptureActivity;
import com.yzq.zxinglibrary.bean.ZxingConfig;
import com.yzq.zxinglibrary.common.Constant;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

import static android.app.Activity.RESULT_OK;
import static com.scy.android.xiaodai.common.Constants.REQUEST_CODE_SCAN;
import static com.yzq.zxinglibrary.decode.ImageUtil.isDownloadsDocument;
import static com.yzq.zxinglibrary.decode.ImageUtil.isExternalStorageDocument;
import static com.yzq.zxinglibrary.decode.ImageUtil.isMediaDocument;

/**
 * created by scy on 2019/6/29 09:45
 * gmail：cherseey@gmail.com
 */
public class MoreFragment extends BaseFragment {

    private static final String TAG = "MoreFragment";
    private static final int REQUEST_CODE_IMAGE_CAMERA = 1;
    private static final int REQUEST_CODE_IMAGE_OP = 2;
    private static final int REQUEST_CODE_OP = 3;
    private PopupWindow mPopupWindow;
    private LinearLayout ll1, ll2, ll3;
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    ArrayList<ViewBean> mViewBeans;
    private MoreRecyclerViewAdapter mMoreRecyclerViewAdapter;
    @Override
    public int initView() {
        return R.layout.fragment_more;
    }

    @Override
    public void initViewControll() {
        setHasOptionsMenu(true);
        ((AppCompatActivity) mContext).setSupportActionBar(toolBar);
        toolBar.setTitle("推荐");
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        swipeRefresh.setProgressViewEndTarget(false, 300);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefresh.setRefreshing(false);
                    }
                }, 2000);
            }
        });
        recyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));
        initVideo();
        mMoreRecyclerViewAdapter = new MoreRecyclerViewAdapter(mContext, mViewBeans);
        recyclerView.setAdapter(mMoreRecyclerViewAdapter);
        mMoreRecyclerViewAdapter.setOnMyItemClickListener(new MoreRecyclerViewAdapter.onMyItemClickListener() {
            @Override
            public void onMyItemClick(int position, View view) {
                startActivity(VideoActivity.getViewActivityIntent(mContext,mViewBeans.get(position)));
            }
        });

    }

    public void initVideo() {
            mViewBeans = new ArrayList<>();
            mViewBeans.add(new ViewBean("大sao下馆子，四星级酒店吃铁板鱿鱼，18元吃到饱", "http://rbv01.ku6.com/omtSn0z_PTREtneb3GRtGg.mp4", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1563437484&di=4fa4d13cc774579e2aeab18203a84297&imgtype=jpg&er=1&src=http%3A%2F%2Fshp.qpic.cn%2Fqqvideo_ori%2F0%2Fj0889lyw0ot_496_280%2F0", "日常", "39万", "1.6万", "2.43"));
            mViewBeans.add(new ViewBean("一斤肥牛一斤面，大sao做铁锅肥牛焖面吃", "http://rbv01.ku6.com/7lut5JlEO-v6a8K3X9xBNg.mp4", "https://pics1.baidu.com/feed/b58f8c5494eef01fe4a1224954156d21be317df1.jpeg?token=63a8fba88a619db4dcf034f6e346f117&s=BE9A6E8502130DC41A10B42A03007041", "日常", "119万", "1.2万", "5.33"));
            mViewBeans.add(new ViewBean("五斤排骨炖一锅，大sao给小肥羊加餐，大蒜配肉一斤不够吃", "https://key003.ku6.com/movie/1af61f05352547bc8468a40ba2d29a1d.mp4", "https://pics1.baidu.com/feed/fcfaaf51f3deb48f969f444744f4ce2d2ff578a1.jpeg?token=a192aee2ab9ae4362a7eb7caadb019bd&s=60D018C46CD234D24009F4390300C050", "日常", "119万", "1.2万", "5.23"));
            mViewBeans.add(new ViewBean("大sao带小肥羊干活，中午吃一顿好的", "http://rbv01.ku6.com/omtSn0z_PTREtneb3GRtGg.mp4", "https://pic1.zhimg.com/80/v2-82702a8419eef292d7afd2e0972a8866_hd.jpg", "日常", "169万", "18万", "8.51"));
            mViewBeans.add(new ViewBean("重庆火锅底料做五斤香辣螺丝，老爸直呼受不了", "http://rbv01.ku6.com/omtSn0z_PTREtneb3GRtGg.mp4", "https://pic3.zhimg.com/80/v2-d354a4a33bda31b170454660ca1a92ee_hd.jpg", "日常", "69万", "6.7万", "7.34"));
            mViewBeans.add(new ViewBean("大sao下馆子，四星级酒店吃铁板鱿鱼，18元吃到饱", "http://rbv01.ku6.com/7lut5JlEO-v6a8K3X9xBNg.mp4", "https://pic1.zhimg.com/80/v2-2e301d5facbe15919096b552ce874872_hd.jpg", "日常", "179万", "3.4万", "5.13"));
            mViewBeans.add(new ViewBean("一盆肥羊一锅饭，大sao 做了金汤肥羊盖饭", "https://key003.ku6.com/movie/1af61f05352547bc8468a40ba2d29a1d.mp4", "https://pic4.zhimg.com/80/v2-87587e067a0aecc3e1a24763e6c9422d_hd.jpg", "日常", "149万", "4.3万", "6.33"));
    }

    @Override
    public void initData() {
        super.initData();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.toolbar, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.addicon:
                initPupwindow();
                mPopupWindow.showAsDropDown(toolBar, 620, 20);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initPupwindow() {
        //初始化pupwindow
        View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_home_page_pupwindow, null, false);
        mPopupWindow = new PopupWindow(view, 410, 400, true);
        ll1 = view.findViewById(R.id.ll1);
        ll2 = view.findViewById(R.id.ll2);
        ll3 = view.findViewById(R.id.ll3);
        ll1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkCameraPermission(Manifest.permission.CAMERA, Constants.CAMERA_REQUESTCODE);
            }
        });
        ll2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkCameraPermission(Manifest.permission.CAMERA, Constants.CAMERA_face_REQUESTCODE);
            }
        });
        ll3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkCameraPermission(Manifest.permission.CAMERA, Constants.REGISTER_face_REQUESTCODE);
            }
        });
    }

    /**
     * @Params :permission requestcode
     * @Author :scy
     * @Date :2019/7/2
     * description:请求相机权限，并执行扫描操作
     */
    private void checkCameraPermission(String permission, int requestcode) {
        if (ContextCompat.checkSelfPermission(mContext, permission) != PackageManager.PERMISSION_GRANTED) {
            if (shouldShowRequestPermissionRationale(permission)) {
                //用户拒绝了权限
                ToastHelper.showToast("您拒绝再次提醒，请前往设置中手动开启");
            }
            requestPermissions(new String[]{permission}, requestcode);
        } else {
            if (requestcode == Constants.CAMERA_REQUESTCODE) {
                ToastHelper.showToast("开启扫描");
                // TODO: 2019/7/2 扫一扫
                startScan();
            } else if (requestcode == Constants.CAMERA_face_REQUESTCODE) {
                // TODO: 2019/7/2 人脸识别
                startFace();
            } else if (requestcode == Constants.REGISTER_face_REQUESTCODE) {
                // TODO: 2019/7/2 人脸注册
                startRegister();
            }
        }
    }

    /**
     * @Params :
     * @Author :scy
     * @Date :2019/7/2
     * description:开始扫描
     */
    private void startScan() {
        Intent intent = new Intent(mContext, CaptureActivity.class);
        /*ZxingConfig是配置类
         *可以设置是否显示底部布局，闪光灯，相册，
         * 是否播放提示音  震动
         * 设置扫描框颜色等
         * 也可以不传这个参数
         * */
        ZxingConfig config = new ZxingConfig();
        config.setPlayBeep(true);//是否播放扫描声音 默认为true
        config.setShake(true);//是否震动  默认为true
        config.setDecodeBarCode(true);//是否扫描条形码 默认为true
        config.setReactColor(R.color.colorAccent);//设置扫描框四个角的颜色 默认为白色
        config.setFrameLineColor(R.color.colorAccent);//设置扫描框边框颜色 默认无色
        config.setScanLineColor(R.color.colorAccent);//设置扫描线的颜色 默认白色
        config.setFullScreenScan(false);//是否全屏扫描  默认为true  设为false则只会在扫描框中扫描
        intent.putExtra(Constant.INTENT_ZXING_CONFIG, config);
        MoreFragment.this.startActivityForResult(intent, REQUEST_CODE_SCAN);
    }

    //检测是否有注册 的信息，选择工具进行注册
    private void startFace() {
        if (((MainApplication) mContext.getApplicationContext()).mFaceDB.mRegister.isEmpty()) {
            Toast.makeText(mContext, "没有注册人脸，请先注册！", Toast.LENGTH_SHORT).show();
        } else {
            new AlertDialog.Builder(mContext)
                    .setTitle("请选择相机")
                    .setIcon(android.R.drawable.ic_dialog_info)
                    .setItems(new String[]{"后置相机", "前置相机"}, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startDetector(which);
                        }
                    })
                    .show();
        }
    }

    private void startRegister() {
        new AlertDialog.Builder(mContext)
                .setTitle("请选择注册方式")
                .setIcon(android.R.drawable.ic_dialog_info)
                .setItems(new String[]{"打开图片", "拍摄照片"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 1:
                                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                                ContentValues values = new ContentValues(1);
                                values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
                                Uri uri = mContext.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                                ((MainApplication) (mContext.getApplicationContext())).setCaptureImage(uri);
                                intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                                MoreFragment.this.startActivityForResult(intent, REQUEST_CODE_IMAGE_CAMERA);
                                break;
                            case 0:
                                Intent getImageByalbum = new Intent(Intent.ACTION_GET_CONTENT);
                                getImageByalbum.addCategory(Intent.CATEGORY_OPENABLE);
                                getImageByalbum.setType("image/jpeg");
                                MoreFragment.this.startActivityForResult(getImageByalbum, REQUEST_CODE_IMAGE_OP);
                                break;
                            default:
                        }
                    }
                })
                .show();
    }

    private void startDetector(int camera) {
        Intent it = new Intent(mContext, DetecterActivity.class);
        it.putExtra("Camera", camera);
        MoreFragment.this.startActivityForResult(it, REQUEST_CODE_OP);
    }

    private String getPath(Uri uri) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (DocumentsContract.isDocumentUri(mContext, uri)) {
                // ExternalStorageProvider
                if (isExternalStorageDocument(uri)) {
                    final String docId = DocumentsContract.getDocumentId(uri);
                    final String[] split = docId.split(":");
                    final String type = split[0];

                    if ("primary".equalsIgnoreCase(type)) {
                        return Environment.getExternalStorageDirectory() + "/" + split[1];
                    }

                    // TODO handle non-primary volumes
                } else if (isDownloadsDocument(uri)) {

                    final String id = DocumentsContract.getDocumentId(uri);
                    final Uri contentUri = ContentUris.withAppendedId(
                            Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                    return getDataColumn(mContext, contentUri, null, null);
                } else if (isMediaDocument(uri)) {
                    final String docId = DocumentsContract.getDocumentId(uri);
                    final String[] split = docId.split(":");
                    final String type = split[0];

                    Uri contentUri = null;
                    if ("image".equals(type)) {
                        contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                    } else if ("video".equals(type)) {
                        contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                    } else if ("audio".equals(type)) {
                        contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                    }

                    final String selection = "_id=?";
                    final String[] selectionArgs = new String[]{
                            split[1]
                    };

                    return getDataColumn(mContext, contentUri, selection, selectionArgs);
                }
            }
        }
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor actualimagecursor = mContext.getContentResolver().query(uri, proj, null, null, null);
        int actual_image_column_index = actualimagecursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        actualimagecursor.moveToFirst();
        String img_path = actualimagecursor.getString(actual_image_column_index);
        String end = img_path.substring(img_path.length() - 4);
        if (0 != end.compareToIgnoreCase(".jpg") && 0 != end.compareToIgnoreCase(".png")) {
            return null;
        }
        return img_path;
    }

    public static String getDataColumn(Context context, Uri uri, String selection,
                                       String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    private void startRegister(Bitmap mBitmap, String file) {
        Intent it = new Intent(mContext, RegisterActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("imagePath", file);
        it.putExtras(bundle);
        MoreFragment.this.startActivityForResult(it, REQUEST_CODE_OP);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case Constants.CAMERA_REQUESTCODE:
                for (int i = 0; i < permissions.length; i++) {
                    if (grantResults.length > 0 && grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                        ToastHelper.showToast("开始扫描二维码");
                        startScan();
                    } else {
                        ToastHelper.showToast("您拒绝了相机权限,请前往设置中手动开启");
                    }
                }
                break;
            case Constants.CAMERA_face_REQUESTCODE:
                for (int i = 0; i < permissions.length; i++) {
                    if (grantResults.length > 0 && grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                        ToastHelper.showToast("开始扫描人脸");
                        startFace();
                    } else {
                        ToastHelper.showToast("您拒绝了相机权限,请前往设置中手动开启");
                    }
                }
            case Constants.REGISTER_face_REQUESTCODE:
                for (int i = 0; i < permissions.length; i++) {
                    if (grantResults.length > 0 && grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                        ToastHelper.showToast("开始注册人脸");
                        startRegister();
                    } else {
                        ToastHelper.showToast("您拒绝了相机权限,请前往设置中手动开启");
                    }
                }
                break;
            default:
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //接收扫描结果, 跳转到webview
        if (requestCode == REQUEST_CODE_SCAN && resultCode == RESULT_OK) {
            if (data != null) {
                String content = data.getStringExtra(Constant.CODED_CONTENT);
                startActivity(WebVIewActivity.getIntent(mContext, content));
            }
        } else if (requestCode == REQUEST_CODE_OP) {
            Log.i(TAG, "RESULT =" + resultCode);
            if (data == null) {
                return;
            }
            Bundle bundle = data.getExtras();
            String path = bundle.getString("imagePath");
            Log.i(TAG, "path=" + path);
        } else if (requestCode == REQUEST_CODE_IMAGE_OP && resultCode == RESULT_OK) {
            Uri mPath = data.getData();
            String file = getPath(mPath);
            Bitmap bmp = MainApplication.decodeImage(file);
            if (bmp == null || bmp.getWidth() <= 0 || bmp.getHeight() <= 0) {
                Log.e(TAG, "error");
            } else {
                Log.i(TAG, "bmp [" + bmp.getWidth() + "," + bmp.getHeight());
            }
            startRegister(bmp, file);
        } else if (requestCode == REQUEST_CODE_IMAGE_CAMERA && resultCode == RESULT_OK) {
            Uri mPath = ((MainApplication) (mContext.getApplicationContext())).getCaptureImage();
            String file = getPath(mPath);
            Bitmap bmp = MainApplication.decodeImage(file);
            startRegister(bmp, file);
        }
    }

}
