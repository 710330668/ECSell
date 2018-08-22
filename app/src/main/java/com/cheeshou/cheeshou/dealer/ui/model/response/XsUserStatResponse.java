package com.cheeshou.cheeshou.dealer.ui.model.response;

import java.util.List;

/**
 * Created by 71033 on 2018/8/19.
 */
public class XsUserStatResponse {


    /**
     * code : 200
     * data : [{"customerCount":0,"enterCount":0,"phoneCount":0,"successCount":0,"userId":"1b6ce670df034cf5b6bd20cb8fac18bf","userName":"销售"},{"customerCount":0,"enterCount":0,"phoneCount":0,"successCount":0,"userId":"2a24119c20a04624982ad65305fca433","userName":"cys002","userPic":"http://39.104.136.205:8888/user/20180814/1534213425288.png"},{"customerCount":0,"enterCount":0,"phoneCount":0,"successCount":0,"userId":"3bb92f806760451480ff3f7a85013d81","userName":"cys002","userPic":"http://39.104.136.205:8888/user/20180814/1534213184776.png"},{"customerCount":0,"enterCount":0,"phoneCount":0,"successCount":0,"userId":"41142ae2fc7d4d6c8f511be6ebda5239","userName":"nnsz","userPic":"http://39.104.136.205:8888/user/20180814/1534227228777.jpg"},{"customerCount":0,"enterCount":0,"phoneCount":0,"successCount":0,"userId":"415da807f4e24c9bbf992823568f8d0f","userName":"","userPic":"http://39.104.136.205:8888/user/20180814/1534216906919.jpg"},{"customerCount":0,"enterCount":0,"phoneCount":0,"successCount":0,"userId":"4743bf6b577e4916bd855c5859503c55","userName":"111","userPic":"http://39.104.136.205:8888/user/20180814/1534223821855.jpg"},{"customerCount":0,"enterCount":0,"phoneCount":0,"successCount":0,"userId":"a9c2ce2b3ef040bb811bddea5bf7508f","userName":"张晓光001","userPic":"http://39.104.136.205:8888/user/20180814/1534211851529.png"},{"customerCount":0,"enterCount":0,"phoneCount":0,"successCount":0,"userId":"cf871d0c0c56424589303042a299adcb","userName":"111","userPic":"http://39.104.136.205:8888/user/20180814/1534225322793.jpg"}]
     * msg : 操作成功
     */

    private int code;
    private String msg;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * customerCount : 0
         * enterCount : 0
         * phoneCount : 0
         * successCount : 0
         * userId : 1b6ce670df034cf5b6bd20cb8fac18bf
         * userName : 销售
         * userPic : http://39.104.136.205:8888/user/20180814/1534213425288.png
         */

        private int customerCount;
        private int enterCount;
        private int phoneCount;
        private int successCount;
        private String userId;
        private String userName;
        private String userPic;

        public int getCustomerCount() {
            return customerCount;
        }

        public void setCustomerCount(int customerCount) {
            this.customerCount = customerCount;
        }

        public int getEnterCount() {
            return enterCount;
        }

        public void setEnterCount(int enterCount) {
            this.enterCount = enterCount;
        }

        public int getPhoneCount() {
            return phoneCount;
        }

        public void setPhoneCount(int phoneCount) {
            this.phoneCount = phoneCount;
        }

        public int getSuccessCount() {
            return successCount;
        }

        public void setSuccessCount(int successCount) {
            this.successCount = successCount;
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

        public String getUserPic() {
            return userPic;
        }

        public void setUserPic(String userPic) {
            this.userPic = userPic;
        }
    }
}
