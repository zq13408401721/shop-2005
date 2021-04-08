package com.sprout.mode.data;

public class NewGoodTopBean {
    /**
     * errno : 0
     * errmsg :
     * data : {"bannerInfo":{"url":"","name":"大家都在买的严选好物","img_url":"http://yanxuan.nosdn.127.net/8976116db321744084774643a933c5ce.png"}}
     */

    private int errno;
    private String errmsg;
    private DataBean data;

    public int getErrno() {
        return errno;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * bannerInfo : {"url":"","name":"大家都在买的严选好物","img_url":"http://yanxuan.nosdn.127.net/8976116db321744084774643a933c5ce.png"}
         */

        private BannerInfoBean bannerInfo;

        public BannerInfoBean getBannerInfo() {
            return bannerInfo;
        }

        public void setBannerInfo(BannerInfoBean bannerInfo) {
            this.bannerInfo = bannerInfo;
        }

        public static class BannerInfoBean {
            /**
             * url :
             * name : 大家都在买的严选好物
             * img_url : http://yanxuan.nosdn.127.net/8976116db321744084774643a933c5ce.png
             */

            private String url;
            private String name;
            private String img_url;

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getImg_url() {
                return img_url;
            }

            public void setImg_url(String img_url) {
                this.img_url = img_url;
            }
        }
    }
}
