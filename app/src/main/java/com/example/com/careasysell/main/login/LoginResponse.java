package com.example.com.careasysell.main.login;

/**
 * Created by 71033 on 2018/8/1.
 */
public class LoginResponse {


    /**
     * code : 200
     * data : {"token":"7B18867E93F346E6998B7D6A7A39355E","userType":"USER_DTRY"}
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
         * token : 7B18867E93F346E6998B7D6A7A39355E
         * userType : USER_DTRY
         */

        private String token;
        private String userType;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getUserType() {
            return userType;
        }

        public void setUserType(String userType) {
            this.userType = userType;
        }
    }
}
