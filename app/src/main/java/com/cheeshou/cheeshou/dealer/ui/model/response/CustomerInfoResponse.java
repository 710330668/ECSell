package com.cheeshou.cheeshou.dealer.ui.model.response;

public class CustomerInfoResponse {


    /**
     * code : 200
     * data : {"customerId":"2372c8caa9bc4ad885360bf007d721f2","name":"测试到店客户","phone":"18363671108","sex":1,"weBcat":"55"}
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
         * customerId : 2372c8caa9bc4ad885360bf007d721f2
         * name : 测试到店客户
         * phone : 18363671108
         * sex : 1
         * weBcat : 55
         */

        private String customerId;
        private String name;
        private String phone;
        private int sex;
        private String weBcat;

        @Override
        public String toString() {
            return "DataBean{" +
                    "customerId='" + customerId + '\'' +
                    ", name='" + name + '\'' +
                    ", phone='" + phone + '\'' +
                    ", sex=" + sex +
                    ", weBcat='" + weBcat + '\'' +
                    '}';
        }

        public String getCustomerId() {
            return customerId;
        }

        public void setCustomerId(String customerId) {
            this.customerId = customerId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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

        public String getWeBcat() {
            return weBcat;
        }

        public void setWeBcat(String weBcat) {
            this.weBcat = weBcat;
        }
    }
}
