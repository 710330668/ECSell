package com.cheeshou.cheeshou.main.login;

/**
 * Created by 71033 on 2018/8/1.
 */
public class LoginResponse {


    /**
     * code : 200
     * data : {"token":"BB4762B3EA1A48AAA711EC6A652D86EB","userType":"USER_XS","phone":"18363671109","userName":"cys002","userPic":"http://39.104.136.205:8888/user/20180814/1534213425288.png","compnayName":"ddid","address":"fdasdf"}
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
         * token : BB4762B3EA1A48AAA711EC6A652D86EB
         * userType : USER_XS
         * phone : 18363671109
         * userName : cys002
         * userPic : http://39.104.136.205:8888/user/20180814/1534213425288.png
         * compnayName : ddid
         * address : fdasdf
         */

        private String token;
        private String userType;
        private String phone;
        private String userName;
        private String userPic;
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

        public String getUserPic() {
            return userPic;
        }

        public void setUserPic(String userPic) {
            this.userPic = userPic;
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
