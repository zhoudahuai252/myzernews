package com.zhoubenliang.myzernews.module.boxview.ui;

import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
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
        inflater.inflate(R.menu.menu_search_activity,menu);

        MenuItem search = menu.findItem(R.id.search);
        //是搜索框默认展开
        search.expandActionView();
        SearchView searchView = (SearchView)search.getActionView();
        searchView.setQueryHint("搜索文章或者频道");
        searchView.setIconifiedByDefault(false);//设置
        return  true;
    }
}
