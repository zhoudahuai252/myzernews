package com.zhoubenliang.myzernews.module.hot.dao;

import com.zhoubenliang.myzernews.module.hot.bean.HotBean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 作者:MR_zhouBL on 2016/4/22.
 * 邮箱:554524787@qq.com
 */
public interface HotInterface {
    @GET("/daily_hot_new.php")
    Observable<HotBean> getHotData(@Query("_appid") String strParms, @Query("_udid") String strUdid);
}
