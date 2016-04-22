package com.zhoubenliang.myzernews.module.hot.dao;

import com.common.model.http.HttpClient;
import com.zhoubenliang.myzernews.module.boxview.dao.BoxImageInterfacce;

/**
 * 作者:MR_zhouBL on 2016/4/22.
 * 邮箱:554524787@qq.com
 */
public class HotDao {
    private static HotInterface mService;

    public static HotInterface getMainIns() {
        if (mService == null) {
            mService = HttpClient.getIns("http://hotphone.myzaker.com")
                    .createService(HotInterface.class);
        }
        return mService;
    }
}
