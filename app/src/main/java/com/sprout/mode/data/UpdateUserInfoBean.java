package com.sprout.mode.data;

public class UpdateUserInfoBean {

    /**
     * errno : 0
     * errmsg :
     * data : {"uid":"7b965656-70eb-4726-b4a7-c52366861485","username":"z1234","nickname":"李四111","avatar":"http://2002a-zwx.oss-cn-beijing.aliyuncs.com/7b965656-70eb-4726-b4a7-c52366861485/16096606300755698.701909599277.png","birthday":110022,"mark":null}
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
         * uid : 7b965656-70eb-4726-b4a7-c52366861485
         * username : z1234
         * nickname : 李四111
         * avatar : http://2002a-zwx.oss-cn-beijing.aliyuncs.com/7b965656-70eb-4726-b4a7-c52366861485/16096606300755698.701909599277.png
         * birthday : 110022
         * mark : null
         */

        private String uid;
        private String username;
        private String nickname;
        private String avatar;
        private int birthday;
        private Object mark;

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

        public Object getMark() {
            return mark;
        }

        public void setMark(Object mark) {
            this.mark = mark;
        }
    }
}
