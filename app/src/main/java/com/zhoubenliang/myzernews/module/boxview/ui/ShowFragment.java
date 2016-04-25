package com.zhoubenliang.myzernews.module.boxview.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.common.view.base.BaseFragment;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.zhoubenliang.myzernews.App;
import com.zhoubenliang.myzernews.R;
import com.zhoubenliang.myzernews.module.boxview.bean.HotSearchBean;
import com.zhoubenliang.myzernews.ui.activity.WebViewActivity;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import me.kaede.tagview.OnTagClickListener;
import me.kaede.tagview.Tag;
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
    private List<HotSearchBean.DataBean.WordlistBean.WordsBean> mWords;
    private ShowCB mShowCB;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_show;
    }

    @Override
    protected void onInit() {
        mShowCB = (ShowCB) getActivity();
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
        mTagView.setOnTagClickListener(new OnTagClickListener() {
            @Override
            public void onTagClick(Tag tag, int position) {
                mShowCB.call(mWords.get(position).getText());
            }
        });
    }

    public interface ShowCB {
        void call(String string);
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
        Bundle bundle = getArguments();

        if (bundle != null) {
            String strQ = bundle.getString("strQ");
            mLinearLayout.setVisibility(View.GONE);
            //显示progressbar
            mProgressBar.setVisibility(View.VISIBLE);
            mWebView.setVisibility(View.VISIBLE);
            String strUrl = "http://search.myzaker.com/api/?keyword=" + strQ + "&_webcode=2cf89736d14f608f37d8";
            mWebView.loadUrl(strUrl);
        } else {
            //显示tagview
            mLinearLayout.setVisibility(View.VISIBLE);

            String url = "http://search.myzaker.com/api/?&c=keywords";
            StringRequest strRequest = new StringRequest(url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    HotSearchBean hotSearchBean = new Gson().
                            fromJson(response, HotSearchBean.class);
                    if (hotSearchBean.getMsg().equals("ok") && hotSearchBean.getData() != null) {
                        List<HotSearchBean.DataBean.WordlistBean> wordlistBeen =
                                hotSearchBean.getData().getWordlist();
                        showTagView(wordlistBeen);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            App.sRequestQueue.add(strRequest);
        }

    }

    private void showTagView(List<HotSearchBean.DataBean.WordlistBean> wordlistBeen) {
        HotSearchBean.DataBean.WordlistBean listBean = wordlistBeen.get(0);
        mWords = listBean.getWords();
        for (HotSearchBean.DataBean.WordlistBean.WordsBean wordsBean : mWords) {
            Tag tag = new Tag(wordsBean.getText());
            tag.tagTextColor = Color.parseColor("#FFFFFF");
            tag.layoutColor = Color.parseColor("#DDDDDD");
            tag.layoutColorPress = Color.parseColor("#555555");
            //or tag.background = this.getResources().getDrawable(R.drawable.custom_bg);
            tag.radius = 20f;
            tag.tagTextSize = 14f;
            tag.layoutBorderSize = 1f;
            tag.layoutBorderColor = Color.parseColor("#FFFFFF");
            tag.isDeletable = false;
            mTagView.addTag(tag);
        }
    }

    public static class JsSomething {
        @JavascriptInterface
        public void doSome() {

        }
    }
}
