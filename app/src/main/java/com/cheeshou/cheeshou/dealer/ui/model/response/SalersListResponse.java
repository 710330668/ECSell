package com.cheeshou.cheeshou.dealer.ui.model.response;

import java.util.List;

/**
 * Created by 71033 on 2018/8/14.
 */
public class SalersListResponse {

    /**
     * code : 200
     * data : [{"account":"c002","createDate":1534176000000,"phone":"18363671109","sex":0,"userId":"2a24119c20a04624982ad65305fca433","userName":"cys002","userPic":"http://39.104.136.205:8888null"},{"account":"111","createDate":1534176000000,"phone":"12345678912","sex":1,"userId":"4743bf6b577e4916bd855c5859503c55","userName":"111","userPic":"http://39.104.136.205:8888null"},{"account":"c001","createDate":1534176000000,"phone":"18363671109","sex":0,"userId":"3bb92f806760451480ff3f7a85013d81","userName":"cys002","userPic":"http://39.104.136.205:8888null"},{"account":"zxg123","createDate":1534176000000,"phone":"18363671109","sex":0,"userId":"a9c2ce2b3ef040bb811bddea5bf7508f","userName":"张晓光001","userPic":"http://39.104.136.205:8888null"},{"account":"ghhj","createDate":1534176000000,"phone":"13789847050","sex":0,"userId":"41142ae2fc7d4d6c8f511be6ebda5239","userName":"nnsz","userPic":"http://39.104.136.205:8888null"},{"account":"1112","createDate":1534176000000,"phone":"13668847563","sex":1,"userId":"cf871d0c0c56424589303042a299adcb","userName":"111","userPic":"http://39.104.136.205:8888null"},{"account":"zcw","createDate":1534176000000,"phone":"12345678912","sex":0,"userId":"415da807f4e24c9bbf992823568f8d0f","userName":"","userPic":"http://39.104.136.205:8888null"},{"account":"xs123","createDate":1533571200000,"phone":"5555228243","sex":0,"userId":"308c226ede2c4e3b8e2a19e636ad2e4f","userName":"Anna Haro","userPic":"http://39.104.136.205:8888null"},{"account":"xs","createDate":1510416000000,"phone":"18363671108","sex":0,"userId":"1b6ce670df034cf5b6bd20cb8fac18bf","userName":"销售"}]
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
         * account : c002
         * createDate : 1534176000000
         * phone : 18363671109
         * sex : 0
         * userId : 2a24119c20a04624982ad65305fca433
         * userName : cys002
         * userPic : http://39.104.136.205:8888null
         */

        private String account;
        private long createDate;
        private String phone;
        private int sex;
        private String userId;
        private String userName;
        private String userPic;

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public long getCreateDate() {
            return createDate;
        }

        public void setCreateDate(long createDate) {
            this.createDate = createDate;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
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
