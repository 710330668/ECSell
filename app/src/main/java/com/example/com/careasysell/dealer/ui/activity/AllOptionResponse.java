package com.example.com.careasysell.dealer.ui.activity;

import java.util.List;

/**
 * Created by 71033 on 2018/8/5.
 */
public class AllOptionResponse {

    /**
     * code : 200
     * data : {"count":5,"lists":[{"brand":"Agile Automotive","browseNum":7,"carId":"0d76e55458a644d49dc79f3a46f069d5","carSeries":"奥迪A5","carStatusName":"已上架","carUserName":"18866195678","cityName":"蚌埠市","createDate":1532859956000,"imgThumUrl":"http://39.104.136.205:8888/20180729/car/1532859956333_small.jpg","provinceName":"安徽省","saleCommission":2000,"shareNum":4,"shelvesNum":1,"typeName":"国产-现车","vname":"2017款 Sportback 45 TFSI quattro 运动型"},{"brand":"Agile Automotive","browseNum":7,"carId":"0a6a1f00182548cebfa2f4f5b8a375f1","carSeries":"奥迪A5","carStatusName":"已上架","carUserName":"18866195678","cityName":"蚌埠市","createDate":1532859948000,"imgThumUrl":"http://39.104.136.205:8888/20180729/car/1532859948243_small.jpg","provinceName":"安徽省","saleCommission":2000,"shareNum":7,"shelvesNum":1,"typeName":"全国","vname":"2017款 Sportback 45 TFSI quattro 运动型"},{"brand":"Agile Automotive","browseNum":0,"carId":"bc85396b98b94e5a86d2445f81168f56","carSeries":"奥迪A5","carStatusName":"在售","carUserName":"18866195678","cityName":"蚌埠市","createDate":1532859936000,"imgThumUrl":"http://39.104.136.205:8888/20180729/car/1532859935683_small.jpg","provinceName":"安徽省","saleCommission":2000,"shareNum":0,"shelvesNum":0,"typeName":"国产-现车","vname":"2017款 Sportback 45 TFSI quattro 运动型"},{"brand":"Agile Automotive","browseNum":0,"carId":"3237dce78c674c9eae8ac83f3b37eb92","carSeries":"奥迪A5","carStatusName":"在售","carUserName":"18866195678","cityName":"蚌埠市","createDate":1532859923000,"imgThumUrl":"http://39.104.136.205:8888/20180729/car/1532859924535_small.jpg","provinceName":"安徽省","saleCommission":2000,"shareNum":0,"shelvesNum":0,"typeName":"国产-现车","vname":"2017款 Sportback 45 TFSI quattro 运动型"},{"brand":"Agile Automotive","browseNum":0,"carId":"f7fd2cad76e14b6b87b26af55fab5e17","carSeries":"奥迪A5","carStatusName":"在售","carUserName":"18866195678","cityName":"蚌埠市","createDate":1532859769000,"imgThumUrl":"http://39.104.136.205:8888/20180729/car/1532859769622_small.jpg","provinceName":"安徽省","saleCommission":2000,"shareNum":0,"shelvesNum":0,"typeName":"国产-现车","vname":"2017款 Sportback 45 TFSI quattro 运动型"}]}
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
         * count : 5
         * lists : [{"brand":"Agile Automotive","browseNum":7,"carId":"0d76e55458a644d49dc79f3a46f069d5","carSeries":"奥迪A5","carStatusName":"已上架","carUserName":"18866195678","cityName":"蚌埠市","createDate":1532859956000,"imgThumUrl":"http://39.104.136.205:8888/20180729/car/1532859956333_small.jpg","provinceName":"安徽省","saleCommission":2000,"shareNum":4,"shelvesNum":1,"typeName":"国产-现车","vname":"2017款 Sportback 45 TFSI quattro 运动型"},{"brand":"Agile Automotive","browseNum":7,"carId":"0a6a1f00182548cebfa2f4f5b8a375f1","carSeries":"奥迪A5","carStatusName":"已上架","carUserName":"18866195678","cityName":"蚌埠市","createDate":1532859948000,"imgThumUrl":"http://39.104.136.205:8888/20180729/car/1532859948243_small.jpg","provinceName":"安徽省","saleCommission":2000,"shareNum":7,"shelvesNum":1,"typeName":"全国","vname":"2017款 Sportback 45 TFSI quattro 运动型"},{"brand":"Agile Automotive","browseNum":0,"carId":"bc85396b98b94e5a86d2445f81168f56","carSeries":"奥迪A5","carStatusName":"在售","carUserName":"18866195678","cityName":"蚌埠市","createDate":1532859936000,"imgThumUrl":"http://39.104.136.205:8888/20180729/car/1532859935683_small.jpg","provinceName":"安徽省","saleCommission":2000,"shareNum":0,"shelvesNum":0,"typeName":"国产-现车","vname":"2017款 Sportback 45 TFSI quattro 运动型"},{"brand":"Agile Automotive","browseNum":0,"carId":"3237dce78c674c9eae8ac83f3b37eb92","carSeries":"奥迪A5","carStatusName":"在售","carUserName":"18866195678","cityName":"蚌埠市","createDate":1532859923000,"imgThumUrl":"http://39.104.136.205:8888/20180729/car/1532859924535_small.jpg","provinceName":"安徽省","saleCommission":2000,"shareNum":0,"shelvesNum":0,"typeName":"国产-现车","vname":"2017款 Sportback 45 TFSI quattro 运动型"},{"brand":"Agile Automotive","browseNum":0,"carId":"f7fd2cad76e14b6b87b26af55fab5e17","carSeries":"奥迪A5","carStatusName":"在售","carUserName":"18866195678","cityName":"蚌埠市","createDate":1532859769000,"imgThumUrl":"http://39.104.136.205:8888/20180729/car/1532859769622_small.jpg","provinceName":"安徽省","saleCommission":2000,"shareNum":0,"shelvesNum":0,"typeName":"国产-现车","vname":"2017款 Sportback 45 TFSI quattro 运动型"}]
         */

        private int count;
        private List<ListsBean> lists;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<ListsBean> getLists() {
            return lists;
        }

        public void setLists(List<ListsBean> lists) {
            this.lists = lists;
        }

        public static class ListsBean {
            /**
             * brand : Agile Automotive
             * browseNum : 7
             * carId : 0d76e55458a644d49dc79f3a46f069d5
             * carSeries : 奥迪A5
             * carStatusName : 已上架
             * carUserName : 18866195678
             * cityName : 蚌埠市
             * createDate : 1532859956000
             * imgThumUrl : http://39.104.136.205:8888/20180729/car/1532859956333_small.jpg
             * provinceName : 安徽省
             * saleCommission : 2000
             * shareNum : 4
             * shelvesNum : 1
             * typeName : 国产-现车
             * vname : 2017款 Sportback 45 TFSI quattro 运动型
             */

            private String brand;
            private int browseNum;
            private String carId;
            private String carSeries;
            private String carStatusName;
            private String carUserName;
            private String cityName;
            private long createDate;
            private String imgThumUrl;
            private String provinceName;
            private int saleCommission;
            private int shareNum;
            private int shelvesNum;
            private String typeName;
            private String vname;

            public String getBrand() {
                return brand;
            }

            public void setBrand(String brand) {
                this.brand = brand;
            }

            public int getBrowseNum() {
                return browseNum;
            }

            public void setBrowseNum(int browseNum) {
                this.browseNum = browseNum;
            }

            public String getCarId() {
                return carId;
            }

            public void setCarId(String carId) {
                this.carId = carId;
            }

            public String getCarSeries() {
                return carSeries;
            }

            public void setCarSeries(String carSeries) {
                this.carSeries = carSeries;
            }

            public String getCarStatusName() {
                return carStatusName;
            }

            public void setCarStatusName(String carStatusName) {
                this.carStatusName = carStatusName;
            }

            public String getCarUserName() {
                return carUserName;
            }

            public void setCarUserName(String carUserName) {
                this.carUserName = carUserName;
            }

            public String getCityName() {
                return cityName;
            }

            public void setCityName(String cityName) {
                this.cityName = cityName;
            }

            public long getCreateDate() {
                return createDate;
            }

            public void setCreateDate(long createDate) {
                this.createDate = createDate;
            }

            public String getImgThumUrl() {
                return imgThumUrl;
            }

            public void setImgThumUrl(String imgThumUrl) {
                this.imgThumUrl = imgThumUrl;
            }

            public String getProvinceName() {
                return provinceName;
            }

            public void setProvinceName(String provinceName) {
                this.provinceName = provinceName;
            }

            public int getSaleCommission() {
                return saleCommission;
            }

            public void setSaleCommission(int saleCommission) {
                this.saleCommission = saleCommission;
            }

            public int getShareNum() {
                return shareNum;
            }

            public void setShareNum(int shareNum) {
                this.shareNum = shareNum;
            }

            public int getShelvesNum() {
                return shelvesNum;
            }

            public void setShelvesNum(int shelvesNum) {
                this.shelvesNum = shelvesNum;
            }

            public String getTypeName() {
                return typeName;
            }

            public void setTypeName(String typeName) {
                this.typeName = typeName;
            }

            public String getVname() {
                return vname;
            }

            public void setVname(String vname) {
                this.vname = vname;
            }
        }
    }
}
