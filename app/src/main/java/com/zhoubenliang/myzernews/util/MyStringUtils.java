package com.zhoubenliang.myzernews.util;

import android.content.Context;
import android.telephony.TelephonyManager;

/**
 * 作者:MR_zhouBL on 2016/4/23.
 * 邮箱:554524787@qq.com
 */
public class MyStringUtils {

    public static String getDeviceIMIE(Context context) {
        //获得设备imei号
        String imei = ((TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
        return imei;
    }
}
