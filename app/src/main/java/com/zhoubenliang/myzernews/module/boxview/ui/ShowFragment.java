package com.zhoubenliang.myzernews.module.boxview.ui;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.common.view.base.BaseFragment;
import com.orhanobut.logger.Logger;
import com.zhoubenliang.myzernews.R;
import com.zhoubenliang.myzernews.ui.activity.WebViewActivity;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

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
        mWebView.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (mProgressBar != null && newProgress > 80) {

                    mProgressBar.setVisibility(View.GONE);
                }
            }
        });
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String urlN) {
                //对内部URL连接进行重处理
                try {
                    String url = URLDecoder.decode(urlN, "UTF-8");
                    Logger.d(url);
                    String strUrl = getUrlStr(url);
                    Logger.d(strUrl);
                    //跳转Webacitvity加载
                    Intent intent = new Intent(getActivity(), WebViewActivity.class);
                    intent.putExtra("url", strUrl);
                    startActivity(intent);
                   /* getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                        }
                    });*/


                } catch (UnsupportedEncodingException e) {
                    Logger.e(e.getMessage());

                }

                return true;
            }
        });
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
    }

    @Override
    protected void onInitEvent() {

    }

    /**
     * 去掉url中的路径，留下请求参数部分
     *
     * @param strURL url地址
     * @return url请求参数部分
     */
    private static String TruncateUrlPage(String strURL) {
        String strAllParam = null;
        String[] arrSplit = null;

        strURL = strURL.trim().toLowerCase();

        arrSplit = strURL.split("[?]");
        if (strURL.length() > 1) {
            if (arrSplit.length > 1) {
                if (arrSplit[1] != null) {
                    strAllParam = arrSplit[1];
                }
            }
        }

        return strAllParam;
    }

    /**
     * 获取网址
     *
     * @param URL
     * @return
     */
    protected String getUrlStr(String URL) {

        String[] split = URL.split(",");
        for (String s : split) {
            if (s.contains("weburl")) {
                String[] strings = s.split("\"");
                for (String string : strings) {
                    if (string.contains("http")) {
                        return string;
                    }
                }
            }
        }
        return null;
    }

    /**
     * 解析出url参数中的键值对
     * 如 "index.jsp?Action=del&id=123"，解析出Action:del,id:123存入map中
     *
     * @param URL url地址
     * @return url请求参数部分
     */
    public static Map<String, String> URLRequest(String URL) {
        Map<String, String> mapRequest = new HashMap<String, String>();

        String[] arrSplit = null;

        String strUrlParam = TruncateUrlPage(URL);
        if (strUrlParam == null) {
            return mapRequest;
        }
        //每个键值为一组
        arrSplit = strUrlParam.split("[&]");
        for (String strSplit : arrSplit) {
            String[] arrSplitEqual = null;
            arrSplitEqual = strSplit.split("[=]");

            //解析出键值
            if (arrSplitEqual.length > 1) {
                //正确解析
                mapRequest.put(arrSplitEqual[0], arrSplitEqual[1]);

            } else {
                if (arrSplitEqual[0] != "") {
                    //只有参数没有值，不加入
                    mapRequest.put(arrSplitEqual[0], "");
                }
            }
        }
        return mapRequest;
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

    public static class JsSomething {
        @JavascriptInterface
        public void doSome() {

        }
    }
}
