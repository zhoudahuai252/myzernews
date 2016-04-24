package com.zhoubenliang.myzernews.module.boxview.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

import com.common.view.base.BaseActivity;
import com.zhoubenliang.myzernews.R;

import butterknife.Bind;

/**
 * 作者:MR_zhouBL on 2016/4/23.
 * 邮箱:554524787@qq.com
 */
public class SearchActivity extends BaseActivity {
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    private Button mButton;
    private SearchView mSearchView;
    private final static Integer SHOW_PROGRESSBAR = 1;

    @Override
    protected int getLayoutResource() {
        return R.layout.aciivity_search;
    }

    @Override
    protected void onInitView() {
        initToolbar();
    }

    private void initToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mToolbar.setNavigationIcon(R.mipmap.abc_ic_ab_back_mtrl_am_alpha);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }


    @Override
    protected void onInitEvent() {

    }

    @Override
    protected void onLoadData() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search_activity, menu);

        MenuItem search = menu.findItem(R.id.search);
        MenuItem button = menu.findItem(R.id.doSearch);
        //是搜索框默认展开
        search.expandActionView();
        button.expandActionView();
        mSearchView = (SearchView) search.getActionView();
        mButton = (Button) button.getActionView();
        mButton.setText("搜索");
        mButton.setTextColor(getResources().getColor(R.color.light_blue));
        mSearchView.setBackgroundColor(Color.parseColor("#ffffff"));
        mSearchView.setQueryHint("搜索文章或者频道");
        mSearchView.setIconifiedByDefault(false);//设置
        mSearchView.setIconified(true);
        initMenuEvent();
        return true;
    }

    private void initMenuEvent() {
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strQuret = (String) mSearchView.getQuery().toString();
                //搜索
                if (!TextUtils.isEmpty(strQuret)) {
                    ShowFragment showFragment = new ShowFragment();
                    Bundle budle = new Bundle();
                    budle.putString("strQ", strQuret);
                    showFragment.setArguments(budle);
                    showFragment(showFragment);
                    //开始搜索。更新界面

                }
            }
        });
        mSearchView.onWindowFocusChanged(true);
    }

    protected void showFragment(Fragment fragment) {
        getSupportFragmentManager().
                beginTransaction().
                replace(R.id.fl_content, fragment)
                .commit();
    }

    public class MessageEvent {
        public final String message;
        public boolean flag = false;

        public MessageEvent(String message, boolean flag) {
            this.message = message;
            this.flag = flag;
        }
    }
}
