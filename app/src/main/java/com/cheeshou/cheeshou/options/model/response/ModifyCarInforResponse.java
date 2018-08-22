package com.cheeshou.cheeshou.options.model.response;

/**
 * Created by 71033 on 2018/8/21.
 */
public class ModifyCarInforResponse {


    /**
     * code : 200
     * data : {"brand":"AC Schnitzer","carPrice":2,"carSeries":"奥迪RS 4","guidPrice":3,"imgThumUrl":"http://39.104.136.205:8888/car/20180818/1534562444828_small.jpg","insuranceRebates":10,"loanRebates":1,"saleCarPrice":222,"saleId":"0659c7b50f83429b942f0fc74fb4ea8e","vname":"2018款 30周年年型 Sportback 35 TFSI 进取型"}
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
         * brand : AC Schnitzer
         * carPrice : 2
         * carSeries : 奥迪RS 4
         * guidPrice : 3
         * imgThumUrl : http://39.104.136.205:8888/car/20180818/1534562444828_small.jpg
         * insuranceRebates : 10
         * loanRebates : 1
         * saleCarPrice : 222
         * saleId : 0659c7b50f83429b942f0fc74fb4ea8e
         * vname : 2018款 30周年年型 Sportback 35 TFSI 进取型
         */

        private String brand;
        private int carPrice;
        private String carSeries;
        private int guidPrice;
        private String imgThumUrl;
        private int insuranceRebates;
        private int loanRebates;
        private int saleCarPrice;
        private String saleId;
        private String vname;

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public int getCarPrice() {
            return carPrice;
        }

        public void setCarPrice(int carPrice) {
            this.carPrice = carPrice;
        }

        public String getCarSeries() {
            return carSeries;
        }

        public void setCarSeries(String carSeries) {
            this.carSeries = carSeries;
        }

        public int getGuidPrice() {
            return guidPrice;
        }

        public void setGuidPrice(int guidPrice) {
            this.guidPrice = guidPrice;
        }

        public String getImgThumUrl() {
            return imgThumUrl;
        }

        public void setImgThumUrl(String imgThumUrl) {
            this.imgThumUrl = imgThumUrl;
        }

        public int getInsuranceRebates() {
            return insuranceRebates;
        }

        public void setInsuranceRebates(int insuranceRebates) {
            this.insuranceRebates = insuranceRebates;
        }

        public int getLoanRebates() {
            return loanRebates;
        }

        public void setLoanRebates(int loanRebates) {
            this.loanRebates = loanRebates;
        }

        public int getSaleCarPrice() {
            return saleCarPrice;
        }

        public void setSaleCarPrice(int saleCarPrice) {
            this.saleCarPrice = saleCarPrice;
        }

        public String getSaleId() {
            return saleId;
        }

        public void setSaleId(String saleId) {
            this.saleId = saleId;
        }

        public String getVname() {
            return vname;
        }

        public void setVname(String vname) {
            this.vname = vname;
        }
    }
}
