package com.zhoubenliang.myzernews.module.boxview.bean;

import java.util.List;

/**
 * 作者:Administrator on 2016/4/25.
 * 邮箱:554524787@qq.com
 */
public class HotSearchBean {

    /**
     * stat : 1
     * msg : ok
     * data : {"wordlist":[{"pk":"hotwords","words":[{"text":"中国首个航天日"},{"text":"高晓松又上央视"},{"text":"公务员考试泄题"},{"text":"韩国加湿器杀人"},{"text":"李连杰故意打向佐"},{"text":"走错家门装皇上"},{"text":"金星素颜现身机场"},{"text":"中国第一高楼投用"},{"text":"试管婴儿升级当爹"},{"text":"巨星Prince去世"},{"text":"张柏芝遭电话恐吓"},{"text":"女孩酒店内死亡"}]}]}
     */

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
         * pk : hotwords
         * words : [{"text":"中国首个航天日"},{"text":"高晓松又上央视"},{"text":"公务员考试泄题"},{"text":"韩国加湿器杀人"},{"text":"李连杰故意打向佐"},{"text":"走错家门装皇上"},{"text":"金星素颜现身机场"},{"text":"中国第一高楼投用"},{"text":"试管婴儿升级当爹"},{"text":"巨星Prince去世"},{"text":"张柏芝遭电话恐吓"},{"text":"女孩酒店内死亡"}]
         */

        private List<WordlistBean> wordlist;

        public List<WordlistBean> getWordlist() {
            return wordlist;
        }

        public void setWordlist(List<WordlistBean> wordlist) {
            this.wordlist = wordlist;
        }

        public static class WordlistBean {
            private String pk;
            /**
             * text : 中国首个航天日
             */

            private List<WordsBean> words;

            public String getPk() {
                return pk;
            }

            public void setPk(String pk) {
                this.pk = pk;
            }

            public List<WordsBean> getWords() {
                return words;
            }

            public void setWords(List<WordsBean> words) {
                this.words = words;
            }

            public static class WordsBean {
                private String text;

                public String getText() {
                    return text;
                }

                public void setText(String text) {
                    this.text = text;
                }
            }
        }
    }
}
