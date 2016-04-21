package com.zhoubenliang.myzernews.model;


import com.zhoubenliang.myzernews.ui.view.MainView;

/**
 * author meikoz on 2016/4/19.
 * email  meikoz@126.com
 */
public class MainLogicImpl implements MainLogic {

    MainView mMainView;

    @Override
    public void switchNavigation(int id) {


    }

    @Override
    public void attachView(MainView mvpView) {
        this.mMainView = mvpView;
    }
}
