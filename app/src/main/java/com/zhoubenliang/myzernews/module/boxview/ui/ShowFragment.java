package com.zhoubenliang.myzernews.module.boxview.ui;

import android.text.TextUtils;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.common.view.base.BaseFragment;
import com.zhoubenliang.myzernews.R;

import butterknife.Bind;
import me.kaede.tagview.TagView;

/**
 * 作者:MR_zhouBL on 2016/4/24.
 * 邮箱:554524787@qq.com
 */
public class ShowFragment extends BaseFragment {
    @Bind(R.id.ll_show)
    LinearLayout mLinearLayout;
    @Bind(R.id.tagview)
    TagView mTagView;
    @Bind(R.id.web_show)
    WebView mWebView;
    @Bind(R.id.progressbar)
    ProgressBar mProgressBar;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_show;
    }

    @Override
    protected void onInit() {
        mWebView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (mProgressBar != null&&newProgress>80) {

                    mProgressBar.setVisibility(View.GONE);
                }
            }
        });
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
    }

    @Override
    protected void onInitEvent() {

    }

    @Override
    protected void onLoadData() {
        String strQ = getArguments().getString("strQ");
        if (!TextUtils.isEmpty(strQ)) {
            mLinearLayout.setVisibility(View.GONE);
            //显示progressbar
            mProgressBar.setVisibility(View.VISIBLE);
            mWebView.setVisibility(View.VISIBLE);
            String strUrl = "http://search.myzaker.com/api/?keyword=" + strQ + "&_webcode=2cf89736d14f608f37d8";
            mWebView.loadUrl(strUrl);
        }

    }

    public static class JsSomething{
        @JavascriptInterface
        public void doSome(){

        }
    }
}
