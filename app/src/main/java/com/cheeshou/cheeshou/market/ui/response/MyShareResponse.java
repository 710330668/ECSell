package com.cheeshou.cheeshou.market.ui.response;


import java.util.List;

public class MyShareResponse {

    /**
     * code : 200
     * data : {"count":0,"lists":[]}
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
         * count : 0
         * lists : []
         */

        private int count;
        private List<ShareCarBean> lists;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<ShareCarBean> getLists() {
            return lists;
        }

        public void setLists(List<ShareCarBean> lists) {
            this.lists = lists;
        }
    }

    public static class ShareCarBean {
        private String shareId;
        private String browseNum;
        private String createDate;
        private List<SaleCarInfosBean> saleCarInfos;

        public String getShareId() {
            return shareId;
        }

        public void setShareId(String shareId) {
            this.shareId = shareId;
        }

        public String getBrowseNum() {
            return browseNum;
        }

        public void setBrowseNum(String browseNum) {
            this.browseNum = browseNum;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public List<SaleCarInfosBean> getSaleCarInfos() {
            return saleCarInfos;
        }

        public void setSaleCarInfos(List<SaleCarInfosBean> saleCarInfos) {
            this.saleCarInfos = saleCarInfos;
        }
    }

    public static class SaleCarInfosBean {
        private String brand;
        private String carPrice;
        private String carSeries;
        private String imgThumUrl;
        private String saleCarPrice;
        private String saleId;
        private String vname;

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public String getCarPrice() {
            return carPrice;
        }

        public void setCarPrice(String carPrice) {
            this.carPrice = carPrice;
        }

        public String getCarSeries() {
            return carSeries;
        }

        public void setCarSeries(String carSeries) {
            this.carSeries = carSeries;
        }

        public String getImgThumUrl() {
            return imgThumUrl;
        }

        public void setImgThumUrl(String imgThumUrl) {
            this.imgThumUrl = imgThumUrl;
        }

        public String getSaleCarPrice() {
            return saleCarPrice;
        }

        public void setSaleCarPrice(String saleCarPrice) {
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
