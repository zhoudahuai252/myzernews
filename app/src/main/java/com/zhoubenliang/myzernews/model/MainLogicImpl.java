package com.zhoubenliang.myzernews.model;

import com.matto.R;
import com.matto.ui.view.MainView;

/**
 * author meikoz on 2016/4/19.
 * email  meikoz@126.com
 */
public class MainLogicImpl implements MainLogic {

    MainView mMainView;

    @Override
    public void switchNavigation(int id) {

        switch (id) {
            case R.id.navigation_selection:
                mMainView.switchHome();
                break;
            case R.id.navigation_discovery:
                mMainView.switchDiscovery();
                break;
            case R.id.navigation_about:
                mMainView.switchShomeMe();
                break;
            default:
                break;
        }
    }

    @Override
    public void attachView(MainView mvpView) {
        this.mMainView = mvpView;
    }
}
