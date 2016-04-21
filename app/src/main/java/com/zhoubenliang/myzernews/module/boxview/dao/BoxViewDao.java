package com.zhoubenliang.myzernews.module.boxview.dao;

import com.common.model.http.HttpClient;

/**
 * 作者:Administrator on 2016/4/21.
 * 邮箱:554524787@qq.com
 */
public class BoxViewDao {
    private static BoxImageInterfacce mService;

    public static BoxImageInterfacce getMainIns() {
        if (mService == null) {
            mService = HttpClient.getIns("http://iphone.myzaker.com")
                    .createService(BoxImageInterfacce.class);
        }
        return mService;
    }

}
