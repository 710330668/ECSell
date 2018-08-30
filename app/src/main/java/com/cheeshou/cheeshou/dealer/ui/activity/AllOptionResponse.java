package com.cheeshou.cheeshou.dealer.ui.activity;

import java.util.List;

/**
 * Created by 71033 on 2018/8/5.
 */
public class AllOptionResponse {


    /**
     * code : 200
     * data : {"count":3,"lists":[{"advicePrice":10,"brand":"奔驰","browseNum":0,"carId":"130598baa2f940f1b7a78407b7bea2a5","carPrice":50,"carSeries":"捷豹XE","carStatusName":"已上架","carUserName":"车源商负责人2","carUserPhone":"13869647062","cityCode":"370200","cityName":"青岛市","createDate":1535599304000,"guidPrice":59,"imgThumUrl":"http://39.104.136.205:8888/car/20180830/1535599304479_small.jpg","provinceCode":"370000","provinceName":"山东省","saleArea":"华北","saleCarPrice":60,"saleCommission":4000,"saleId":"de233f406b5d4cbdaa71fe567d8b7bde","shareNum":0,"shelvesNum":1,"vname":"2018款 2.0T 300PS 四驱R-Sport运动版"},{"advicePrice":10,"brand":"AC Schnitzer","browseNum":0,"carId":"a3f86e38240345e489b9518a97112bfc","carPrice":10,"carSeries":"奥迪RS 4","carStatusName":"已上架","carUserName":"车源商负责人2","carUserPhone":"13869647062","cityCode":"370200","cityName":"青岛市","createDate":1535549324000,"guidPrice":2,"imgThumUrl":"http://39.104.136.205:8888/car/20180830/1535598095620_small.jpg","provinceCode":"370000","provinceName":"山东省","saleArea":"北京","saleCarPrice":12,"saleCommission":10,"saleId":"0c15bff5e8c6476eb6e95752892b3ad6","shareNum":0,"shelvesNum":1,"vname":"2018款 30周年年型 Sportback 35 TFSI 进取型"},{"advicePrice":10,"brand":"AC Schnitzer","browseNum":50,"carId":"8c777efacec2453789b867c06d580821","carPrice":2,"carSeries":"奥迪RS 4","carStatusName":"已上架","carUserName":"车源商负责人2","carUserPhone":"13869647062","cityCode":"340100","cityName":"合肥市","createDate":1534562562000,"guidPrice":3,"imgThumUrl":"http://39.104.136.205:8888/car/20180830/1535598177995_small.jpg","provinceCode":"340000","provinceName":"安徽省","saleArea":"北京","saleCarPrice":2.6,"saleCommission":1,"saleId":"eef6e0ad870240fc809f770834ae7afd","shareNum":0,"shelvesNum":2,"vname":"2018款 30周年年型 Sportback 35 TFSI 进取型"}]}
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
         * count : 3
         * lists : [{"advicePrice":10,"brand":"奔驰","browseNum":0,"carId":"130598baa2f940f1b7a78407b7bea2a5","carPrice":50,"carSeries":"捷豹XE","carStatusName":"已上架","carUserName":"车源商负责人2","carUserPhone":"13869647062","cityCode":"370200","cityName":"青岛市","createDate":1535599304000,"guidPrice":59,"imgThumUrl":"http://39.104.136.205:8888/car/20180830/1535599304479_small.jpg","provinceCode":"370000","provinceName":"山东省","saleArea":"华北","saleCarPrice":60,"saleCommission":4000,"saleId":"de233f406b5d4cbdaa71fe567d8b7bde","shareNum":0,"shelvesNum":1,"vname":"2018款 2.0T 300PS 四驱R-Sport运动版"},{"advicePrice":10,"brand":"AC Schnitzer","browseNum":0,"carId":"a3f86e38240345e489b9518a97112bfc","carPrice":10,"carSeries":"奥迪RS 4","carStatusName":"已上架","carUserName":"车源商负责人2","carUserPhone":"13869647062","cityCode":"370200","cityName":"青岛市","createDate":1535549324000,"guidPrice":2,"imgThumUrl":"http://39.104.136.205:8888/car/20180830/1535598095620_small.jpg","provinceCode":"370000","provinceName":"山东省","saleArea":"北京","saleCarPrice":12,"saleCommission":10,"saleId":"0c15bff5e8c6476eb6e95752892b3ad6","shareNum":0,"shelvesNum":1,"vname":"2018款 30周年年型 Sportback 35 TFSI 进取型"},{"advicePrice":10,"brand":"AC Schnitzer","browseNum":50,"carId":"8c777efacec2453789b867c06d580821","carPrice":2,"carSeries":"奥迪RS 4","carStatusName":"已上架","carUserName":"车源商负责人2","carUserPhone":"13869647062","cityCode":"340100","cityName":"合肥市","createDate":1534562562000,"guidPrice":3,"imgThumUrl":"http://39.104.136.205:8888/car/20180830/1535598177995_small.jpg","provinceCode":"340000","provinceName":"安徽省","saleArea":"北京","saleCarPrice":2.6,"saleCommission":1,"saleId":"eef6e0ad870240fc809f770834ae7afd","shareNum":0,"shelvesNum":2,"vname":"2018款 30周年年型 Sportback 35 TFSI 进取型"}]
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
             * advicePrice : 10
             * brand : 奔驰
             * browseNum : 0
             * carId : 130598baa2f940f1b7a78407b7bea2a5
             * carPrice : 50
             * carSeries : 捷豹XE
             * carStatusName : 已上架
             * carUserName : 车源商负责人2
             * carUserPhone : 13869647062
             * cityCode : 370200
             * cityName : 青岛市
             * createDate : 1535599304000
             * guidPrice : 59
             * imgThumUrl : http://39.104.136.205:8888/car/20180830/1535599304479_small.jpg
             * provinceCode : 370000
             * provinceName : 山东省
             * saleArea : 华北
             * saleCarPrice : 60
             * saleCommission : 4000
             * saleId : de233f406b5d4cbdaa71fe567d8b7bde
             * shareNum : 0
             * shelvesNum : 1
             * vname : 2018款 2.0T 300PS 四驱R-Sport运动版
             */

            private int advicePrice;
            private String brand;
            private int browseNum;
            private String carId;
            private int carPrice;
            private String carSeries;
            private String carStatusName;
            private String carUserName;
            private String carUserPhone;
            private String cityCode;
            private String cityName;
            private long createDate;
            private int guidPrice;
            private String imgThumUrl;
            private String provinceCode;
            private String provinceName;
            private String saleArea;
            private float saleCarPrice;
            private int saleCommission;
            private String saleId;
            private int shareNum;
            private int shelvesNum;
            private String vname;

            public int getAdvicePrice() {
                return advicePrice;
            }

            public void setAdvicePrice(int advicePrice) {
                this.advicePrice = advicePrice;
            }

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

            public String getCarUserPhone() {
                return carUserPhone;
            }

            public void setCarUserPhone(String carUserPhone) {
                this.carUserPhone = carUserPhone;
            }

            public String getCityCode() {
                return cityCode;
            }

            public void setCityCode(String cityCode) {
                this.cityCode = cityCode;
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

            public String getProvinceCode() {
                return provinceCode;
            }

            public void setProvinceCode(String provinceCode) {
                this.provinceCode = provinceCode;
            }

            public String getProvinceName() {
                return provinceName;
            }

            public void setProvinceName(String provinceName) {
                this.provinceName = provinceName;
            }

            public String getSaleArea() {
                return saleArea;
            }

            public void setSaleArea(String saleArea) {
                this.saleArea = saleArea;
            }

            public float getSaleCarPrice() {
                return saleCarPrice;
            }

            public void setSaleCarPrice(int saleCarPrice) {
                this.saleCarPrice = saleCarPrice;
            }

            public int getSaleCommission() {
                return saleCommission;
            }

            public void setSaleCommission(int saleCommission) {
                this.saleCommission = saleCommission;
            }

            public String getSaleId() {
                return saleId;
            }

            public void setSaleId(String saleId) {
                this.saleId = saleId;
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

            public String getVname() {
                return vname;
            }

            public void setVname(String vname) {
                this.vname = vname;
            }
        }
    }
}
