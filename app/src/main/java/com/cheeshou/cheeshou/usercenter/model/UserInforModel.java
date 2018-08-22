package com.cheeshou.cheeshou.usercenter.model;

/**
 * Created by 71033 on 2018/8/4.
 */
public class UserInforModel {


    /**
     * code : 200
     * data : {"account":"cys","isCert":"0","userName":"山东青岛","userType":"USER_DTRY"}
     * msg : 操作成功
     */

    private int code;
    private DataBean data;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        /**
         * account : cys
         * isCert : 0
         * userName : 山东青岛
         * userType : USER_DTRY
         */

        private String account;
        private String isCert;
        private String userName;
        private String userType;

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getIsCert() {
            return isCert;
        }

        public void setIsCert(String isCert) {
            this.isCert = isCert;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserType() {
            return userType;
        }

        public void setUserType(String userType) {
            this.userType = userType;
        }
    }
}
