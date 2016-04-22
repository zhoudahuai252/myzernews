package com.zhoubenliang.myzernews.ui.activity;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.common.view.base.BaseActivity;
import com.zhoubenliang.myzernews.R;

import butterknife.Bind;

/**
 * 作者:Administrator on 2016/4/22.
 * 邮箱:554524787@qq.com
 */
public class WebViewActivity extends BaseActivity {
    @Bind(R.id.web_show)
    WebView mWebView;
    @Bind(R.id.progressbar)
    ProgressBar mProgressBar;
    private String mStrUrl;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_webview_layout;
    }

    @Override
    protected void onInitView() {
        mToolbar.setTitle("网页");
    }

    @Override
    protected void onInitEvent() {

        mStrUrl = getIntent().getStringExtra("url");
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                mProgressBar.setVisibility(View.GONE);
            }
        });
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
    }

    @Override
    protected void onLoadData() {
        mWebView.loadUrl(mStrUrl);
    }
}
