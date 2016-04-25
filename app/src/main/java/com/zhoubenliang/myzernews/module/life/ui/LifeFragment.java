package com.zhoubenliang.myzernews.module.life.ui;

import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bartoszlipinski.recyclerviewheader2.RecyclerViewHeader;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.common.view.base.BaseFragment;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.se7en.utils.SystemUtil;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;
import com.zhoubenliang.myzernews.App;
import com.zhoubenliang.myzernews.R;
import com.zhoubenliang.myzernews.module.life.adapter.MyLifeAdapter;
import com.zhoubenliang.myzernews.module.life.bean.LIfeBean;

import butterknife.Bind;

/**
 * author meikoz on 2016/4/19.
 * email  meikoz@126.com
 */
public class LifeFragment extends BaseFragment {
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.recyclerview_life)
    PullLoadMoreRecyclerView mRecyclerView;
    @Bind(R.id.cbanner)
    ConvenientBanner mConvenientBanner;
    @Bind(R.id.iv_left)
    ImageView mImageViewLeft;
    @Bind(R.id.iv_right)
    ImageView mImageViewRigth;
    @Bind(R.id.header)
    RecyclerViewHeader mRecyclerViewHeader;
    private MyLifeAdapter adapter;
    private Button mButton;
    private String nextUrl;
    private String startUrl = "http://wl.myzaker.com/?c=columns&p=0";
    private LIfeBean mIfeBean;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_life;
    }

    @Override
    protected void onLoadData() {
        String cityName = SystemUtil.getSharedString("city");
        if (!TextUtils.isEmpty(cityName)) {
            mButton.setText(cityName);
        } else
            mButton.setText("北京");
        nextUrl = "http://wl.myzaker.com/?c=columns&p=0";
        doHHtpReues(nextUrl);

    }

    private void doHHtpReues(String url) {
        StringRequest strQuset = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                mIfeBean = new Gson().fromJson(response, LIfeBean.class);
                nextUrl = mIfeBean.getData().getInfo().getNext_url();
                Logger.d(nextUrl);
                adapter.refreshData(mIfeBean);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Logger.e(error.getMessage());
            }
        });
        App.sRequestQueue.add(strQuset);
    }

    private void initToolBar() {
        mToolbar.setTitle("玩乐");
        mToolbar.inflateMenu(R.menu.menu_life_fragment);
        MenuItem menuItem = mToolbar.getMenu().findItem(R.id.life_menu);
        menuItem.expandActionView();
        mButton = (Button) menuItem.getActionView();
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return false;
            }
        });

    }

    @Override
    protected void onInit() {
        initToolBar();
        mIfeBean = new LIfeBean();
        adapter = new MyLifeAdapter(getActivity(), mIfeBean);
        mRecyclerView.setLinearLayout();
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    protected void onInitEvent() {

    }
}
