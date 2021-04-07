package com.sprout.mode.data;

import java.util.List;

public class ChannelBean {

    /**
     * code : 0
     * data : [{"id":73,"name":"健康","category":"jiankang","orderNo":1,"channelType":3,"channelUrl":"https://h.topjoytec.com/h/news/df/index?type=jiankang","status":1,"createdTime":"2018-04-08 15:28:06.248","updatedTime":"2018-05-17 14:05:29.831"},{"id":68,"name":"科技","category":"keji","orderNo":1,"channelType":3,"channelUrl":"https://h.topjoytec.com/h/news/df/index?type=keji","status":1,"createdTime":"2018-04-08 15:28:06.248","updatedTime":"2018-05-17 14:31:16.124"},{"id":67,"name":"国际","category":"guoji","orderNo":1,"channelType":3,"channelUrl":"https://h.topjoytec.com/h/news/df/index?type=guoji","status":1,"createdTime":"2018-04-08 15:28:06.248","updatedTime":"2018-05-17 14:31:17.819"},{"id":64,"name":"头条","category":"toutiao","orderNo":1,"channelType":3,"channelUrl":"https://h.topjoytec.com/h/news/df/index?type=toutiao","status":1,"createdTime":"2018-04-08 15:28:06.248","updatedTime":"2018-05-17 13:36:15.981"},{"id":66,"name":"国内","category":"guonei","orderNo":1,"channelType":3,"channelUrl":"https://h.topjoytec.com/h/news/df/index?type=guonei","status":1,"createdTime":"2018-04-08 15:28:06.248","updatedTime":"2018-05-17 14:05:31.136"},{"id":65,"name":"社会","category":"shehui","orderNo":1,"channelType":3,"channelUrl":"https://h.topjoytec.com/h/news/df/index?type=shehui","status":1,"createdTime":"2018-04-08 15:28:06.248","updatedTime":"2018-05-17 14:31:19.075"},{"id":91,"name":"游戏中心","category":"game","orderNo":2,"channelType":4,"channelUrl":"http://www.shandw.com/m/indexTemp/?channel=14388","platform":"Android","status":1,"createdTime":"2020-09-29 16:43:40.544","updatedTime":"2020-09-29 16:43:40.544"}]
     */

    private int code;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 73
         * name : 健康
         * category : jiankang
         * orderNo : 1
         * channelType : 3
         * channelUrl : https://h.topjoytec.com/h/news/df/index?type=jiankang
         * status : 1
         * createdTime : 2018-04-08 15:28:06.248
         * updatedTime : 2018-05-17 14:05:29.831
         * platform : Android
         */

        private int id;
        private String name;
        private String category;
        private int orderNo;
        private int channelType;
        private String channelUrl;
        private int status;
        private String createdTime;
        private String updatedTime;
        private String platform;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public int getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(int orderNo) {
            this.orderNo = orderNo;
        }

        public int getChannelType() {
            return channelType;
        }

        public void setChannelType(int channelType) {
            this.channelType = channelType;
        }

        public String getChannelUrl() {
            return channelUrl;
        }

        public void setChannelUrl(String channelUrl) {
            this.channelUrl = channelUrl;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getCreatedTime() {
            return createdTime;
        }

        public void setCreatedTime(String createdTime) {
            this.createdTime = createdTime;
        }

        public String getUpdatedTime() {
            return updatedTime;
        }

        public void setUpdatedTime(String updatedTime) {
            this.updatedTime = updatedTime;
        }

        public String getPlatform() {
            return platform;
        }

        public void setPlatform(String platform) {
            this.platform = platform;
        }
    }
}
