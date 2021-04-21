package com.sprout.mode.data;

public class VersionBean {

    /**
     * code : 200
     * errmsg :
     * data : {"versionCode":10,"version":"1.5.9","name":"Shop","url":"http://cdwan.cn:7000/tongpao/apk/shop_1.5.9.apk","date":"2021.5.12","description":"更新xxxbug,添加xxx功能"}
     */

    private int code;
    private String errmsg;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
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
         * versionCode : 10
         * version : 1.5.9
         * name : Shop
         * url : http://cdwan.cn:7000/tongpao/apk/shop_1.5.9.apk
         * date : 2021.5.12
         * description : 更新xxxbug,添加xxx功能
         */

        private int versionCode;
        private String version;
        private String name;
        private String url;
        private String date;
        private String description;

        public String getPackagename() {
            return packagename;
        }

        public void setPackagename(String packagename) {
            this.packagename = packagename;
        }

        private String packagename;

        public int getVersionCode() {
            return versionCode;
        }

        public void setVersionCode(int versionCode) {
            this.versionCode = versionCode;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}
