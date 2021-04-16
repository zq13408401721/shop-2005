package com.sprout.mode.data;

public class WXOrderBean {

    /**
     * code : 200
     * err :
     * data : {"prepay_id":"wx16100858556809011a32960138e16f0000","sign":"225CBE37589C7E33D9B3A97C9D42C8B8","timestamp":"1618538939","noncestr":"q2dbodmm49etqnsf","money":50}
     */

    private int code;
    private String err;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getErr() {
        return err;
    }

    public void setErr(String err) {
        this.err = err;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * prepay_id : wx16100858556809011a32960138e16f0000
         * sign : 225CBE37589C7E33D9B3A97C9D42C8B8
         * timestamp : 1618538939
         * noncestr : q2dbodmm49etqnsf
         * money : 50
         */

        private String prepay_id;
        private String sign;
        private String timestamp;
        private String noncestr;
        private int money;

        public String getPrepay_id() {
            return prepay_id;
        }

        public void setPrepay_id(String prepay_id) {
            this.prepay_id = prepay_id;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public String getNoncestr() {
            return noncestr;
        }

        public void setNoncestr(String noncestr) {
            this.noncestr = noncestr;
        }

        public int getMoney() {
            return money;
        }

        public void setMoney(int money) {
            this.money = money;
        }
    }
}
