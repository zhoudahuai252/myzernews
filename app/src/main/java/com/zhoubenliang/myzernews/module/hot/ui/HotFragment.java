package com.zhoubenliang.myzernews.module.hot.ui;

import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.common.view.base.BaseFragment;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;
import com.zhoubenliang.myzernews.App;
import com.zhoubenliang.myzernews.R;
import com.zhoubenliang.myzernews.module.hot.adapter.HotRecyAdapter;
import com.zhoubenliang.myzernews.module.hot.bean.ArticlesBean;
import com.zhoubenliang.myzernews.module.hot.bean.HotBean;
import com.zhoubenliang.myzernews.module.hot.dao.HotDao;
import com.zhoubenliang.myzernews.util.MyStringUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * author meikoz on 2016/4/19.
 * email  meikoz@126.com
 */
public class HotFragment extends BaseFragment {
    @Bind(R.id.lv_hot)
    PullLoadMoreRecyclerView mRecyclerView;
    private String mDeviceIMIE;
    private List<ArticlesBean> mArticlesBeen;
    private HotRecyAdapter mAdapter;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_hot_layout;
    }

    @Override
    protected void onInit() {
        mArticlesBeen = new ArrayList<>();
        mToolbar.setTitle("热点新闻");
        mDeviceIMIE = MyStringUtils.getDeviceIMIE(getActivity());
//        String IMEI =android.os.SystemProperties.get(android.telephony.TelephonyProperties.PROPERTY_IMEI)
        mAdapter = new HotRecyAdapter(getActivity(), mArticlesBeen);
    }

    @Override
    protected void onInitEvent() {
        mRecyclerView.setLinearLayout();
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setPushRefreshEnable(false);
        mRecyclerView.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.
                PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                onLoadData();
            }

            @Override
            public void onLoadMore() {

            }
        });
    }

    @Override
    protected void onLoadData() {
        doHttpUseVolley();
//        doHttpUseRe();
    }

    private void doHttpUseVolley() {
        String url = "http://hotphone.myzaker.com/daily_hot_new.php?&_udid=";
        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                List<ArticlesBean> articles = new Gson().
                        fromJson(response, HotBean.class)
                        .getData().getArticles();
                if (articles != null) {
                    if (articles.size()==0){
                        Toast.makeText(getActivity(), "没有最新数据",
                                Toast.LENGTH_SHORT).show();
                    }
                    mArticlesBeen.addAll(articles);
                    mAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getActivity(), "没有最新数据",
                            Toast.LENGTH_SHORT).show();
                }
                mRecyclerView.setPullLoadMoreCompleted();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Logger.e(error.getMessage());
                Log.e("print", error.getMessage());
                mRecyclerView.setPullLoadMoreCompleted();
            }
        });
        App.sRequestQueue.add(request);
    }

    private void doHttpUseRe() {
        Observable<HotBean> hotBeanObservable = HotDao
                .getMainIns()
                .getHotData(mDeviceIMIE);
        Logger.d(mDeviceIMIE);
        hotBeanObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<HotBean>() {
                    @Override
                    public void call(HotBean hotBean) {
                        //获取网络返回的数据
                        List<ArticlesBean> articlesBeen = hotBean.
                                getData().getArticles();
                        if (articlesBeen != null) {

                            mArticlesBeen.addAll(0, articlesBeen);
                            mAdapter.notifyDataSetChanged();
                        } else {
                            //
                            Toast.makeText(getActivity(), "没有数据",
                                    Toast.LENGTH_SHORT).show();
                        }
                        mRecyclerView.setPullLoadMoreCompleted();
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        //异常情况
                        Logger.e(throwable.getMessage());
                        mRecyclerView.setPullLoadMoreCompleted();
                    }
                });
    }
}
