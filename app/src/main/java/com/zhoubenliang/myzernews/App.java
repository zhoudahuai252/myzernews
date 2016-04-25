package com.zhoubenliang.myzernews;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.common.BasicApplication;
import com.common.model.control.LogicProxy;
import com.se7en.utils.DeviceUtils;
import com.se7en.utils.SystemUtil;
import com.zhoubenliang.myzernews.model.LoginLogic;
import com.zhoubenliang.myzernews.model.MainLogic;

/**
 * 作者:MR_zhouBL on 2016/4/20.
 * 邮箱:554524787@qq.com
 */
public class App extends BasicApplication {
    public static RequestQueue sRequestQueue;

    @Override
    public void onCreate() {
        super.onCreate();
        SystemUtil.setContext(this);
        DeviceUtils.setContext(this);
        sRequestQueue = Volley.newRequestQueue(this);

        LogicProxy.getInstance().init(
                LoginLogic.class, MainLogic.class
        );
    }


}
