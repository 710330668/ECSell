package com.cheeshou.cheeshou.dealer.ui.activity;

import java.util.List;

/**
 * Created by 71033 on 2018/8/5.
 */
public class AllOptionResponse {

    /**
     * code : 200
     * data : {"count":1,"lists":[{"advicePrice":10,"brand":"Agile Automotive","browseNum":433,"carId":"0a6a1f00182548cebfa2f4f5b8a375f1","carPrice":130,"carSeries":"奥迪A5","carStatusName":"已预订","carUserName":"山东青岛","carUserPhone":"13869647062","cityName":"蚌埠市","createDate":1532859948000,"guidPrice":240,"imgThumUrl":"http://39.104.136.205:8888/car/20180815/1534304526428_small.jpg","provinceName":"安徽省","saleCommission":2,"shareNum":8,"shelvesNum":3,"typeName":"全国","vname":"2017款 Sportback 45 TFSI quattro 运动型"}]}
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
         * count : 1
         * lists : [{"advicePrice":10,"brand":"Agile Automotive","browseNum":433,"carId":"0a6a1f00182548cebfa2f4f5b8a375f1","carPrice":130,"carSeries":"奥迪A5","carStatusName":"已预订","carUserName":"山东青岛","carUserPhone":"13869647062","cityName":"蚌埠市","createDate":1532859948000,"guidPrice":240,"imgThumUrl":"http://39.104.136.205:8888/car/20180815/1534304526428_small.jpg","provinceName":"安徽省","saleCommission":2,"shareNum":8,"shelvesNum":3,"typeName":"全国","vname":"2017款 Sportback 45 TFSI quattro 运动型"}]
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
             * brand : Agile Automotive
             * browseNum : 433
             * carId : 0a6a1f00182548cebfa2f4f5b8a375f1
             * carPrice : 130
             * carSeries : 奥迪A5
             * carStatusName : 已预订
             * carUserName : 山东青岛
             * carUserPhone : 13869647062
             * cityName : 蚌埠市
             * createDate : 1532859948000
             * guidPrice : 240
             * imgThumUrl : http://39.104.136.205:8888/car/20180815/1534304526428_small.jpg
             * provinceName : 安徽省
             * saleCommission : 2
             * shareNum : 8
             * shelvesNum : 3
             * typeName : 全国
             * vname : 2017款 Sportback 45 TFSI quattro 运动型
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
            private String cityName;
            private long createDate;
            private int guidPrice;
            private String imgThumUrl;
            private String provinceName;
            private int saleCommission;
            private int shareNum;
            private int shelvesNum;
            private String typeName;
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
