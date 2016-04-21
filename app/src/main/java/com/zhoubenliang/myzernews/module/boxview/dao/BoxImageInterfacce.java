package com.zhoubenliang.myzernews.module.boxview.dao;

import com.zhoubenliang.myzernews.module.boxview.bean.BoxViewImageBean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 作者:Administrator on 2016/4/21.
 * 邮箱:554524787@qq.com
 */
public interface BoxImageInterfacce {
        @GET("/zaker/follow_promote.php")
        Observable<BoxViewImageBean> getData(@Query("_appid") String strPrams);
}
