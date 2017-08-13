package com.bway.two.model.bean;

/**
 * @类的用途:
 * @姓名: 张惠行
 * @date 2017/8/13
 */

public class RegisterBean {

    /**
     * code : 1000
     * descirption : 系统处理成功
     * object : {"phone":"18335473224","createTime":1502622465000,"nickname":"大道用户3224","userId":126,"picture":"","token":"3a2ef4a2a7f26a43349cea6c1d9464a7"}
     */

    private String code;
    private String descirption;
    private ObjectBean object;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescirption() {
        return descirption;
    }

    public void setDescirption(String descirption) {
        this.descirption = descirption;
    }

    public ObjectBean getObject() {
        return object;
    }

    public void setObject(ObjectBean object) {
        this.object = object;
    }

    public static class ObjectBean {
        /**
         * phone : 18335473224
         * createTime : 1502622465000
         * nickname : 大道用户3224
         * userId : 126
         * picture :
         * token : 3a2ef4a2a7f26a43349cea6c1d9464a7
         */

        private String phone;
        private long createTime;
        private String nickname;
        private int userId;
        private String picture;
        private String token;

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
