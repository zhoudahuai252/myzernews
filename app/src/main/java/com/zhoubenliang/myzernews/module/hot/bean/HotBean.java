package com.zhoubenliang.myzernews.module.hot.bean;

import java.util.List;

/**
 * 作者:MR_zhouBL on 2016/4/22.
 * 邮箱:554524787@qq.com
 */
public class HotBean {



    private String stat;
    private String msg;


    private DataBean data;

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * title : 今日看点
         * block_title : 今日看点
         * stitle :
         * skey :
         * pic : http://upload.myzaker.com/data/image/logo/1057.png?1418985329
         * root_type : normal
         * top_position_index : -1
         */

        private BlockInfoBean block_info;


        private InfoBean info;
        /**
         * title : 转发至新浪微博
         * block_pk : 100000
         * share_url : http://wbapi.myzaker.com/weibo/api_post.php?act=post_article
         * action_type : sendForward
         * require_pk : Y
         * require_title : Y
         * require_web_url : Y
         */

        private List<ShareBean> share;


        private List<ArticlesBean> articles;

        public BlockInfoBean getBlock_info() {
            return block_info;
        }

        public void setBlock_info(BlockInfoBean block_info) {
            this.block_info = block_info;
        }

        public InfoBean getInfo() {
            return info;
        }

        public void setInfo(InfoBean info) {
            this.info = info;
        }

        public List<ShareBean> getShare() {
            return share;
        }

        public void setShare(List<ShareBean> share) {
            this.share = share;
        }

        public List<ArticlesBean> getArticles() {
            return articles;
        }

        public void setArticles(List<ArticlesBean> articles) {
            this.articles = articles;
        }

        public static class BlockInfoBean {
            private String title;
            private String block_title;
            private String stitle;
            private String skey;
            private String pic;
            private String root_type;
            private String top_position_index;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getBlock_title() {
                return block_title;
            }

            public void setBlock_title(String block_title) {
                this.block_title = block_title;
            }

            public String getStitle() {
                return stitle;
            }

            public void setStitle(String stitle) {
                this.stitle = stitle;
            }

            public String getSkey() {
                return skey;
            }

            public void setSkey(String skey) {
                this.skey = skey;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getRoot_type() {
                return root_type;
            }

            public void setRoot_type(String root_type) {
                this.root_type = root_type;
            }

            public String getTop_position_index() {
                return top_position_index;
            }

            public void setTop_position_index(String top_position_index) {
                this.top_position_index = top_position_index;
            }
        }

        public static class InfoBean {
            private String next_url;
            private String pre_url;
            private String comment_list_url;
            private String comment_url;
            private String comment_reply_url;
            private String comment_count_url;
            private String like_count_url;
            private String like_save_url;
            private String like_remove_url;
            private String readstat;
            private String localremove_url;
            private String localsave_url;
            private String tuijian_list_url;
            private String force_refresh;

            public String getNext_url() {
                return next_url;
            }

            public void setNext_url(String next_url) {
                this.next_url = next_url;
            }

            public String getPre_url() {
                return pre_url;
            }

            public void setPre_url(String pre_url) {
                this.pre_url = pre_url;
            }

            public String getComment_list_url() {
                return comment_list_url;
            }

            public void setComment_list_url(String comment_list_url) {
                this.comment_list_url = comment_list_url;
            }

            public String getComment_url() {
                return comment_url;
            }

            public void setComment_url(String comment_url) {
                this.comment_url = comment_url;
            }

            public String getComment_reply_url() {
                return comment_reply_url;
            }

            public void setComment_reply_url(String comment_reply_url) {
                this.comment_reply_url = comment_reply_url;
            }

            public String getComment_count_url() {
                return comment_count_url;
            }

            public void setComment_count_url(String comment_count_url) {
                this.comment_count_url = comment_count_url;
            }

            public String getLike_count_url() {
                return like_count_url;
            }

            public void setLike_count_url(String like_count_url) {
                this.like_count_url = like_count_url;
            }

            public String getLike_save_url() {
                return like_save_url;
            }

            public void setLike_save_url(String like_save_url) {
                this.like_save_url = like_save_url;
            }

            public String getLike_remove_url() {
                return like_remove_url;
            }

            public void setLike_remove_url(String like_remove_url) {
                this.like_remove_url = like_remove_url;
            }

            public String getReadstat() {
                return readstat;
            }

            public void setReadstat(String readstat) {
                this.readstat = readstat;
            }

            public String getLocalremove_url() {
                return localremove_url;
            }

            public void setLocalremove_url(String localremove_url) {
                this.localremove_url = localremove_url;
            }

            public String getLocalsave_url() {
                return localsave_url;
            }

            public void setLocalsave_url(String localsave_url) {
                this.localsave_url = localsave_url;
            }

            public String getTuijian_list_url() {
                return tuijian_list_url;
            }

            public void setTuijian_list_url(String tuijian_list_url) {
                this.tuijian_list_url = tuijian_list_url;
            }

            public String getForce_refresh() {
                return force_refresh;
            }

            public void setForce_refresh(String force_refresh) {
                this.force_refresh = force_refresh;
            }
        }

        public static class ShareBean {
            private String title;
            private String block_pk;
            private String share_url;
            private String action_type;
            private String require_pk;
            private String require_title;
            private String require_web_url;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getBlock_pk() {
                return block_pk;
            }

            public void setBlock_pk(String block_pk) {
                this.block_pk = block_pk;
            }

            public String getShare_url() {
                return share_url;
            }

            public void setShare_url(String share_url) {
                this.share_url = share_url;
            }

            public String getAction_type() {
                return action_type;
            }

            public void setAction_type(String action_type) {
                this.action_type = action_type;
            }

            public String getRequire_pk() {
                return require_pk;
            }

            public void setRequire_pk(String require_pk) {
                this.require_pk = require_pk;
            }

            public String getRequire_title() {
                return require_title;
            }

            public void setRequire_title(String require_title) {
                this.require_title = require_title;
            }

            public String getRequire_web_url() {
                return require_web_url;
            }

            public void setRequire_web_url(String require_web_url) {
                this.require_web_url = require_web_url;
            }
        }


    }
}
