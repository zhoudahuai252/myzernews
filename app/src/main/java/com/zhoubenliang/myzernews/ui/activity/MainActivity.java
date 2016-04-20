package com.zhoubenliang.myzernews.ui.activity;


import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;

import com.common.model.control.LogicProxy;
import com.common.view.base.BaseActivity;
import com.zhoubenliang.myzernews.R;
import com.zhoubenliang.myzernews.model.MainLogic;
import com.zhoubenliang.myzernews.ui.fragment.DiscoveryFragment;
import com.zhoubenliang.myzernews.ui.fragment.HomeFragment;
import com.zhoubenliang.myzernews.ui.fragment.ShowMeFragment;
import com.zhoubenliang.myzernews.ui.view.MainView;

import butterknife.OnClick;


public class MainActivity extends BaseActivity implements MainView {


    public static void start(Activity context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
        context.finish();
    }

    MainLogic mainLogic;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    protected void onInitView() {
        if (mainLogic == null)
            mainLogic = LogicProxy.getInstance().getBindViewProxy(MainLogic.class, this);
        switchHome();
    }

    @Override
    public void switchHome() {
        startFragment(new HomeFragment());
    }

    @Override
    public void switchDiscovery() {
        startFragment(new DiscoveryFragment());
    }

    @Override
    public void switchShomeMe() {
        startFragment(new ShowMeFragment());
    }

    void startFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_layout, fragment)
                .commit();
    }


    @OnClick({R.id.navigation_selection, R.id.navigation_discovery, R.id.navigation_about})
    void onClick(View view) {
        mainLogic.switchNavigation(view.getId());
    }
}
