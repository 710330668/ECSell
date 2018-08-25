package com.cheeshou.cheeshou.dealer.ui.model.response;

import java.io.Serializable;

/**
 * Created by 71033 on 2018/8/13.
 */
public class XsUserDetailResponse implements Serializable{


    /**
     * code : 200
     * data : {"account":"c002","createDate":1534176000000,"phone":"18363671109","sex":0,"userId":"2a24119c20a04624982ad65305fca433","userName":"cys002","userPic":"http://39.104.136.205:8888/user/20180814/1534213425288.png","weChat":"123456"}
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

    public static class DataBean implements Serializable{
        /**
         * account : c002
         * createDate : 1534176000000
         * phone : 18363671109
         * sex : 0
         * userId : 2a24119c20a04624982ad65305fca433
         * userName : cys002
         * userPic : http://39.104.136.205:8888/user/20180814/1534213425288.png
         * weChat : 123456
         */

        private String account;
        private long createDate;
        private String phone;
        private int sex;
        private String userId;
        private String userName;
        private String userPic;
        private String weChat;

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

        public String getWeChat() {
            return weChat;
        }

        public void setWeChat(String weChat) {
            this.weChat = weChat;
        }
    }
}
