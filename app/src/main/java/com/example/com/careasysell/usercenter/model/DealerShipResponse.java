package com.example.com.careasysell.usercenter.model;

/**
 * Created by 71033 on 2018/8/19.
 */
public class DealerShipResponse {


    /**
     * code : 200
     * data : {"account":"cys","cityName":"白山市","comName":"122","comPic":"http://39.104.136.205:8888/company/20180819/1534656724903.png","headName":"zzz","headPhone":"18363674459","provinceName":"吉林省","receptionNum":0,"scale":"小","staffNum":2,"storeArea":2}
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
         * cityName : 白山市
         * comName : 122
         * comPic : http://39.104.136.205:8888/company/20180819/1534656724903.png
         * headName : zzz
         * headPhone : 18363674459
         * provinceName : 吉林省
         * receptionNum : 0
         * scale : 小
         * staffNum : 2
         * storeArea : 2
         */

        private String account;
        private String cityName;
        private String comName;
        private String comPic;
        private String headName;
        private String headPhone;
        private String provinceName;
        private int receptionNum;
        private String scale;
        private int staffNum;
        private int storeArea;

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getCityName() {
            return cityName;
        }

        public void setCityName(String cityName) {
            this.cityName = cityName;
        }

        public String getComName() {
            return comName;
        }

        public void setComName(String comName) {
            this.comName = comName;
        }

        public String getComPic() {
            return comPic;
        }

        public void setComPic(String comPic) {
            this.comPic = comPic;
        }

        public String getHeadName() {
            return headName;
        }

        public void setHeadName(String headName) {
            this.headName = headName;
        }

        public String getHeadPhone() {
            return headPhone;
        }

        public void setHeadPhone(String headPhone) {
            this.headPhone = headPhone;
        }

        public String getProvinceName() {
            return provinceName;
        }

        public void setProvinceName(String provinceName) {
            this.provinceName = provinceName;
        }

        public int getReceptionNum() {
            return receptionNum;
        }

        public void setReceptionNum(int receptionNum) {
            this.receptionNum = receptionNum;
        }

        public String getScale() {
            return scale;
        }

        public void setScale(String scale) {
            this.scale = scale;
        }

        public int getStaffNum() {
            return staffNum;
        }

        public void setStaffNum(int staffNum) {
            this.staffNum = staffNum;
        }

        public int getStoreArea() {
            return storeArea;
        }

        public void setStoreArea(int storeArea) {
            this.storeArea = storeArea;
        }
    }
}
