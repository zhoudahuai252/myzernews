package com.zhoubenliang.myzernews;

import com.common.BasicApplication;
import com.common.model.control.LogicProxy;
import com.zhoubenliang.myzernews.model.LoginLogic;
import com.zhoubenliang.myzernews.model.MainLogic;

/**
 * 作者:MR_zhouBL on 2016/4/20.
 * 邮箱:554524787@qq.com
 */
public class App extends BasicApplication{
    @Override
    public void onCreate() {
        super.onCreate();
        LogicProxy.getInstance().init(
                LoginLogic.class, MainLogic.class
        );
    }
}
