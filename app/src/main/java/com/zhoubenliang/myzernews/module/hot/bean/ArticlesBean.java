package com.zhoubenliang.myzernews.module.hot.bean;

import java.util.List;

/**
 * 作者:MR_zhouBL on 2016/4/23.
 * 邮箱:554524787@qq.com
 */
public class ArticlesBean {
    private String pk;
    private String app_ids;
    private String title;
    private String date;
    private String auther_name;
    private String weburl;
    private String is_full;
    private String content;
    private String full_url;
    /**
     * show_jingcai : Y
     * item_type : 3
     */

    private SpecialInfoBean special_info;
    private String url;
    /**
     * type : image
     * id : 571a110fa07aec3d330039bb
     * url : http://zkres.myzaker.com/201604/571a110fa07aec3d330039bb_640.jpg
     * m_url : http://zkres.myzaker.com/201604/571a110fa07aec3d330039bb_320.jpg
     * min_url : http://zkres.myzaker.com/201604/571a110fa07aec3d330039bb_120.jpg
     * raw_url : http://inews.gtimg.com/newsapp_match/0/263650313/0
     * w : 600
     * h : 400
     */

    private List<ThumbnailMediasBean> thumbnail_medias;

    public String getPk() {
        return pk;
    }

    public void setPk(String pk) {
        this.pk = pk;
    }

    public String getApp_ids() {
        return app_ids;
    }

    public void setApp_ids(String app_ids) {
        this.app_ids = app_ids;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAuther_name() {
        return auther_name;
    }

    public void setAuther_name(String auther_name) {
        this.auther_name = auther_name;
    }

    public String getWeburl() {
        return weburl;
    }

    public void setWeburl(String weburl) {
        this.weburl = weburl;
    }

    public String getIs_full() {
        return is_full;
    }

    public void setIs_full(String is_full) {
        this.is_full = is_full;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFull_url() {
        return full_url;
    }

    public void setFull_url(String full_url) {
        this.full_url = full_url;
    }

    public SpecialInfoBean getSpecial_info() {
        return special_info;
    }

    public void setSpecial_info(SpecialInfoBean special_info) {
        this.special_info = special_info;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<ThumbnailMediasBean> getThumbnail_medias() {
        return thumbnail_medias;
    }

    public void setThumbnail_medias(List<ThumbnailMediasBean> thumbnail_medias) {
        this.thumbnail_medias = thumbnail_medias;
    }

    public static class SpecialInfoBean {
        private String show_jingcai;
        private String item_type;

        public String getShow_jingcai() {
            return show_jingcai;
        }

        public void setShow_jingcai(String show_jingcai) {
            this.show_jingcai = show_jingcai;
        }

        public String getItem_type() {
            return item_type;
        }

        public void setItem_type(String item_type) {
            this.item_type = item_type;
        }
    }

    public static class ThumbnailMediasBean {
        private String type;
        private String id;
        private String url;
        private String m_url;
        private String min_url;
        private String raw_url;
        private String w;
        private String h;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getM_url() {
            return m_url;
        }

        public void setM_url(String m_url) {
            this.m_url = m_url;
        }

        public String getMin_url() {
            return min_url;
        }

        public void setMin_url(String min_url) {
            this.min_url = min_url;
        }

        public String getRaw_url() {
            return raw_url;
        }

        public void setRaw_url(String raw_url) {
            this.raw_url = raw_url;
        }

        public String getW() {
            return w;
        }

        public void setW(String w) {
            this.w = w;
        }

        public String getH() {
            return h;
        }

        public void setH(String h) {
            this.h = h;
        }
    }
}