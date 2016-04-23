package com.zhoubenliang.myzernews.module.hot.dao;

import com.common.model.http.HttpClient;
import com.orhanobut.logger.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 作者:MR_zhouBL on 2016/4/22.
 * 邮箱:554524787@qq.com
 */
public class HotDao {
    private static HotInterface mService;

    public static HotInterface getMainIns() {
        if (mService == null) {
            mService = HttpClient.getIns("http://hotphone.myzaker.com/")
                    .createService(HotInterface.class);
        }
        return mService;
    }

    public static String doHttpRequsetByGet(String parms) {
        String strResult=null;
        String strUrl = "http://hotphone.myzaker.com/daily_hot_new.php?&_udid=";
        try {
            URL url = new URL(strUrl + parms);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream is = conn.getInputStream();
                 strResult = getString(is);
                Logger.json(strResult);
                is.close();
            }
            conn.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strResult;
    }

    /**
     * inputstream to String type
     *
     * @param is
     * @return
     */
    public static String getString(InputStream is) {
        String str = null;
        try {
            if (is != null) {
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(is, "UTF-8"));
                String line = null;
                StringBuffer sb = new StringBuffer();
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                str = sb.toString();
                if (str.startsWith("<html>"))   //获取xml或者json数据，如果获取到的数据为xml，则为null
                {
                    str = null;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }
}
