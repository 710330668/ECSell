package com.cheeshou.cheeshou.main.login;

/**
 * Created by 71033 on 2018/8/1.
 */
public class LoginResponse {


    /**
     * code : 200
     * data : {"token":"030D665798144FB4AECED38D9402C5C8","userType":"USER_CYS","phone":"13869647062","userName":"山东青岛","compnayName":"122","address":"fasdfads"}
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
         * token : 030D665798144FB4AECED38D9402C5C8
         * userType : USER_CYS
         * phone : 13869647062
         * userName : 山东青岛
         * compnayName : 122
         * address : fasdfads
         */

        private String token;
        private String userType;
        private String phone;
        private String userName;
        private String compnayName;
        private String address;

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

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getCompnayName() {
            return compnayName;
        }

        public void setCompnayName(String compnayName) {
            this.compnayName = compnayName;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }
}
