package com.cheeshou.cheeshou.options.model.response;

/**
 * Created by 71033 on 2018/8/21.
 */
public class ModifyCarInforResponse {


    /**
     * code : 200
     * data : {"brand":"AC Schnitzer","carPrice":1,"carSeries":"奥迪RS 4","guidPrice":2,"imgThumUrl":"http://39.104.136.205:8888/car/20180907/1536326458997_small.jpg","insuranceRebates":0,"loanRebates":0,"saleCarPrice":1.01,"saleId":"54f0298d26274049af52534a4b8070b5","vname":"2018款 30周年年型 Sportback 35 TFSI 进取型"}
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
         * carPrice : 1
         * carSeries : 奥迪RS 4
         * guidPrice : 2
         * imgThumUrl : http://39.104.136.205:8888/car/20180907/1536326458997_small.jpg
         * insuranceRebates : 0
         * loanRebates : 0
         * saleCarPrice : 1.01
         * saleId : 54f0298d26274049af52534a4b8070b5
         * vname : 2018款 30周年年型 Sportback 35 TFSI 进取型
         */

        private String brand;
        private float carPrice;
        private String carSeries;
        private float guidPrice;
        private String imgThumUrl;
        private int insuranceRebates;
        private int loanRebates;
        private double saleCarPrice;
        private String saleId;
        private String vname;

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public float getCarPrice() {
            return carPrice;
        }

        public void setCarPrice(float carPrice) {
            this.carPrice = carPrice;
        }

        public String getCarSeries() {
            return carSeries;
        }

        public void setCarSeries(String carSeries) {
            this.carSeries = carSeries;
        }

        public float getGuidPrice() {
            return guidPrice;
        }

        public void setGuidPrice(float guidPrice) {
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

        public double getSaleCarPrice() {
            return saleCarPrice;
        }

        public void setSaleCarPrice(double saleCarPrice) {
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
