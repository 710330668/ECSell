package com.example.com.careasysell.dealer.ui.model.response;

import java.util.List;

/**
 * Created by 71033 on 2018/8/13.
 */
public class XsUserResponse {


    /**
     * code : 200
     * data : {"count":2,"userModels":[{"account":"xs123","createDate":1533571200000,"phone":"5555228243","sex":0,"userId":"308c226ede2c4e3b8e2a19e636ad2e4f","userName":"Anna Haro"},{"account":"xs","createDate":1510416000000,"phone":"18363671108","sex":0,"userId":"1b6ce670df034cf5b6bd20cb8fac18bf","userName":"销售"}]}
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
         * count : 2
         * userModels : [{"account":"xs123","createDate":1533571200000,"phone":"5555228243","sex":0,"userId":"308c226ede2c4e3b8e2a19e636ad2e4f","userName":"Anna Haro"},{"account":"xs","createDate":1510416000000,"phone":"18363671108","sex":0,"userId":"1b6ce670df034cf5b6bd20cb8fac18bf","userName":"销售"}]
         */

        private int count;
        private List<UserModelsBean> userModels;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<UserModelsBean> getUserModels() {
            return userModels;
        }

        public void setUserModels(List<UserModelsBean> userModels) {
            this.userModels = userModels;
        }

        public static class UserModelsBean {
            /**
             * account : xs123
             * createDate : 1533571200000
             * phone : 5555228243
             * sex : 0
             * userId : 308c226ede2c4e3b8e2a19e636ad2e4f
             * userName : Anna Haro
             */

            private String account;
            private long createDate;
            private String phone;
            private int sex;
            private String userId;
            private String userName;

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
        }
    }
}
