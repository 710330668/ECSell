package com.cheeshou.cheeshou.usercenter.model;

/**
 * Created by 71033 on 2018/8/4.
 */
public class UserInforModel {


    /**
     * code : 200
     * data : {"account":"xs","address":"","companyName":"ddid","isCert":"0","userId":"1b6ce670df034cf5b6bd20cb8fac18bf","userName":"销售","userType":"USER_XS"}
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
         * account : xs
         * address :
         * companyName : ddid
         * isCert : 0
         * userId : 1b6ce670df034cf5b6bd20cb8fac18bf
         * userName : 销售
         * userType : USER_XS
         */

        private String account;
        private String address;
        private String companyName;
        private String isCert;
        private String userId;
        private String userName;
        private String userType;

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public String getIsCert() {
            return isCert;
        }

        public void setIsCert(String isCert) {
            this.isCert = isCert;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
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
