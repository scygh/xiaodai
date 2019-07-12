package com.scy.android.xiaodai.view.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.scy.android.xiaodai.R;
import com.scy.android.xiaodai.utils.ToastHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WebVIewActivity extends BaseActivity {

    private static final String TAG = "WebVIewActivity";
    @BindView(R.id.webview)
    WebView webview;
    @BindView(R.id.progressbar)
    ProgressBar progressbar;
    @BindView(R.id.webview_back)
    ImageView webviewBack;
    @BindView(R.id.webview_title)
    TextView webviewTitle;
    private String url;

    public static Intent getIntent(Context c, String url) {
        Intent intent = new Intent(c, WebVIewActivity.class);
        intent.putExtra("url", url);
        return intent;
    }

    @Override
    public int getContentView() {
        return R.layout.activity_web_view;
    }

    @Override
    public void initView() {
        url = getIntent().getStringExtra("url");
        webview.loadUrl(url);
        webview.addJavascriptInterface(this, "android");
        webview.setWebChromeClient(mWebChromeClient);
        webview.setWebViewClient(mWebViewClient);
        WebSettings settings = webview.getSettings();
        settings.setJavaScriptEnabled(true);
        //不使用缓存，只从网络获取数据.
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        //支持屏幕缩放
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);

        webviewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (webview.canGoBack()) {
                    webview.goBack();
                } else {
                    finish();
                }
            }
        });
    }

    private WebChromeClient mWebChromeClient = new WebChromeClient() {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            progressbar.setProgress(newProgress);
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            webviewTitle.setText(title);
        }

        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            AlertDialog.Builder localBuilder = new AlertDialog.Builder(webview.getContext());
            localBuilder.setMessage(message).setPositiveButton("确定", null);
            localBuilder.setCancelable(false);
            localBuilder.create().show();
            result.confirm();
            return true;

        }
    };

    private WebViewClient mWebViewClient = new WebViewClient() {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            if (url.equals("http://www.google.com/")) {
                ToastHelper.showToast("国内不能访问google,拦截该url");
                return true;//表示我已经处理过了
            }
            return super.shouldOverrideUrlLoading(view, url);

        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            progressbar.setVisibility(View.VISIBLE);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            progressbar.setVisibility(View.VISIBLE);
        }
    };

    @JavascriptInterface
    public void getClient() {
        Log.d(TAG, "getClient: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        webview.destroy();
        webview = null;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (webview.canGoBack() && keyCode == KeyEvent.KEYCODE_BACK) {
            //点击返回按钮的时候判断有没有上一页
            webview.goBack();
            //表示返回webView的上一页面
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}

