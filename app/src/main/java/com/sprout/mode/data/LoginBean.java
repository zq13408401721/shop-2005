package com.sprout.mode.data;

public class LoginBean {

    /**
     * errno : 0
     * errmsg :
     * data : {"code":200,"token":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoiN2I5NjU2NTYtNzBlYi00NzI2LWI0YTctYzUyMzY2ODYxNDg1IiwicmFuZG9tIjoicnJrYWxobWJ4aSIsImlhdCI6MTYxODM2NDU0OH0.pqU49eJuxRPelo7fYcFmLQ9hLuIyJu8jWxaPmZ6Yrpk","userInfo":{"uid":"7b965656-70eb-4726-b4a7-c52366861485","username":"z1234","nickname":"21312312","gender":0,"avatar":"http://2002a-zwx.oss-cn-beijing.aliyuncs.com/7b965656-70eb-4726-b4a7-c52366861485/16096606300755698.701909599277.png","birthday":110022}}
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
         * code : 200
         * token : eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoiN2I5NjU2NTYtNzBlYi00NzI2LWI0YTctYzUyMzY2ODYxNDg1IiwicmFuZG9tIjoicnJrYWxobWJ4aSIsImlhdCI6MTYxODM2NDU0OH0.pqU49eJuxRPelo7fYcFmLQ9hLuIyJu8jWxaPmZ6Yrpk
         * userInfo : {"uid":"7b965656-70eb-4726-b4a7-c52366861485","username":"z1234","nickname":"21312312","gender":0,"avatar":"http://2002a-zwx.oss-cn-beijing.aliyuncs.com/7b965656-70eb-4726-b4a7-c52366861485/16096606300755698.701909599277.png","birthday":110022}
         */

        private int code;
        private String token;
        private UserInfoBean userInfo;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public UserInfoBean getLoginUserInfo() {
            return userInfo;
        }

        public void setLoginUserInfo(UserInfoBean userInfo) {
            this.userInfo = userInfo;
        }

        public static class UserInfoBean {
            /**
             * uid : 7b965656-70eb-4726-b4a7-c52366861485
             * username : z1234
             * nickname : 21312312
             * gender : 0
             * avatar : http://2002a-zwx.oss-cn-beijing.aliyuncs.com/7b965656-70eb-4726-b4a7-c52366861485/16096606300755698.701909599277.png
             * birthday : 110022
             */

            private String uid;
            private String username;
            private String nickname;
            private int gender;
            private String avatar;
            private int birthday;

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public int getGender() {
                return gender;
            }

            public void setGender(int gender) {
                this.gender = gender;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public int getBirthday() {
                return birthday;
            }

            public void setBirthday(int birthday) {
                this.birthday = birthday;
            }
        }
    }
}
