package com.example.com.careasysell.options.model.response;

import java.util.List;

/**
 * Created by 71033 on 2018/8/5.
 */
public class CarDetailResponse {


    /**
     * code : 200
     * data : {"advicePrice":10,"brand":"Agile Automotive","browseNum":7,"carFormality":"手续齐全7天内发车","carId":"0d76e55458a644d49dc79f3a46f069d5","carPrice":120,"carSeries":"奥迪A5","carSetting":"高端大气上档次","carStatusName":"已上架","carType":"2","carUserName":"18866195678","carYear":"2018","cityName":"蚌埠市","createDate":1532859956000,"discountContent":"具体优惠","discounts":[{"discountName":"店内上保险，店内置换"}],"guidPrice":240,"imgs":[{"imgThumUrl":"http://39.104.136.205:8888/20180729/car/1532859956333_small.jpg","imgUrl":"http://39.104.136.205:8888/20180729/car/1532859956333.jpg"}],"outsiteColor":"白色","provinceName":"安徽省","remark":"备注","saleArea":"华北","saleCommission":2000,"shareNum":5,"shelvesNum":2,"typeName":"国产-现车","vname":"2017款 Sportback 45 TFSI quattro 运动型","withinColor":"灰"}
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
         * advicePrice : 10
         * brand : Agile Automotive
         * browseNum : 7
         * carFormality : 手续齐全7天内发车
         * carId : 0d76e55458a644d49dc79f3a46f069d5
         * carPrice : 120
         * carSeries : 奥迪A5
         * carSetting : 高端大气上档次
         * carStatusName : 已上架
         * carType : 2
         * carUserName : 18866195678
         * carYear : 2018
         * cityName : 蚌埠市
         * createDate : 1532859956000
         * discountContent : 具体优惠
         * discounts : [{"discountName":"店内上保险，店内置换"}]
         * guidPrice : 240
         * imgs : [{"imgThumUrl":"http://39.104.136.205:8888/20180729/car/1532859956333_small.jpg","imgUrl":"http://39.104.136.205:8888/20180729/car/1532859956333.jpg"}]
         * outsiteColor : 白色
         * provinceName : 安徽省
         * remark : 备注
         * saleArea : 华北
         * saleCommission : 2000
         * shareNum : 5
         * shelvesNum : 2
         * typeName : 国产-现车
         * vname : 2017款 Sportback 45 TFSI quattro 运动型
         * withinColor : 灰
         */

        private int advicePrice;
        private String brand;
        private int browseNum;
        private String carFormality;
        private String carId;
        private int carPrice;
        private String carSeries;
        private String carSetting;
        private String carStatusName;
        private String carType;
        private String carUserName;
        private String carYear;
        private String cityName;
        private long createDate;
        private String discountContent;
        private int guidPrice;
        private String outsiteColor;
        private String provinceName;
        private String remark;
        private String saleArea;
        private int saleCommission;
        private int shareNum;
        private int shelvesNum;
        private String typeName;
        private String vname;
        private String withinColor;
        private List<DiscountsBean> discounts;
        private List<ImgsBean> imgs;

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

        public String getCarFormality() {
            return carFormality;
        }

        public void setCarFormality(String carFormality) {
            this.carFormality = carFormality;
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

        public String getCarSetting() {
            return carSetting;
        }

        public void setCarSetting(String carSetting) {
            this.carSetting = carSetting;
        }

        public String getCarStatusName() {
            return carStatusName;
        }

        public void setCarStatusName(String carStatusName) {
            this.carStatusName = carStatusName;
        }

        public String getCarType() {
            return carType;
        }

        public void setCarType(String carType) {
            this.carType = carType;
        }

        public String getCarUserName() {
            return carUserName;
        }

        public void setCarUserName(String carUserName) {
            this.carUserName = carUserName;
        }

        public String getCarYear() {
            return carYear;
        }

        public void setCarYear(String carYear) {
            this.carYear = carYear;
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

        public String getDiscountContent() {
            return discountContent;
        }

        public void setDiscountContent(String discountContent) {
            this.discountContent = discountContent;
        }

        public int getGuidPrice() {
            return guidPrice;
        }

        public void setGuidPrice(int guidPrice) {
            this.guidPrice = guidPrice;
        }

        public String getOutsiteColor() {
            return outsiteColor;
        }

        public void setOutsiteColor(String outsiteColor) {
            this.outsiteColor = outsiteColor;
        }

        public String getProvinceName() {
            return provinceName;
        }

        public void setProvinceName(String provinceName) {
            this.provinceName = provinceName;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getSaleArea() {
            return saleArea;
        }

        public void setSaleArea(String saleArea) {
            this.saleArea = saleArea;
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

        public String getWithinColor() {
            return withinColor;
        }

        public void setWithinColor(String withinColor) {
            this.withinColor = withinColor;
        }

        public List<DiscountsBean> getDiscounts() {
            return discounts;
        }

        public void setDiscounts(List<DiscountsBean> discounts) {
            this.discounts = discounts;
        }

        public List<ImgsBean> getImgs() {
            return imgs;
        }

        public void setImgs(List<ImgsBean> imgs) {
            this.imgs = imgs;
        }

        public static class DiscountsBean {
            /**
             * discountName : 店内上保险，店内置换
             */

            private String discountName;

            public String getDiscountName() {
                return discountName;
            }

            public void setDiscountName(String discountName) {
                this.discountName = discountName;
            }
        }

        public static class ImgsBean {
            /**
             * imgThumUrl : http://39.104.136.205:8888/20180729/car/1532859956333_small.jpg
             * imgUrl : http://39.104.136.205:8888/20180729/car/1532859956333.jpg
             */

            private String imgThumUrl;
            private String imgUrl;

            public String getImgThumUrl() {
                return imgThumUrl;
            }

            public void setImgThumUrl(String imgThumUrl) {
                this.imgThumUrl = imgThumUrl;
            }

            public String getImgUrl() {
                return imgUrl;
            }

            public void setImgUrl(String imgUrl) {
                this.imgUrl = imgUrl;
            }
        }
    }
}