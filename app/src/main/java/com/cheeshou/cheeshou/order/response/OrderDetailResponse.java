package com.cheeshou.cheeshou.order.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 71033 on 2018/8/16.
 */
public class OrderDetailResponse implements Serializable{


    /**
     * code : 200
     * data : {"advicePrice":100,"brand":"AC Schnitzer","browseNum":12,"carFormality":"不填","carId":"26d24330688e4b93bdeaa43563d6e259","carPrice":1,"carSeries":"奥迪RS 4","carSetting":"13","carStatusName":"已售","carType":"1","carUserName":"车源商负责人2","carYear":"2018","chassisNo":"123","cityCode":"370200","cityName":"青岛市","createDate":1536759891000,"customerName":"555233","discountContent":"1234","discounts":[{"discountName":"店内贷款"},{"discountName":"店内保养"}],"guidPrice":2,"imgs":[{"imgId":"4279cd5646e148b7b808b75d9ecd5c0c","imgThumUrl":"http://39.104.136.205:8888/car/20180907/1536326457482_small.jpg","imgUrl":"http://39.104.136.205:8888/car/20180907/1536326457482.jpg"},{"imgId":"553208bd8dad431e8f79f1a29f1f9cd8","imgThumUrl":"http://39.104.136.205:8888/car/20180907/1536326458325_small.jpg","imgUrl":"http://39.104.136.205:8888/car/20180907/1536326458325.jpg"},{"imgId":"b2002aca168b4c30951cf7deb03497f4","imgThumUrl":"http://39.104.136.205:8888/car/20180907/1536326458997_small.jpg","imgUrl":"http://39.104.136.205:8888/car/20180907/1536326458997.jpg"},{"imgId":"fbcd86b21da14d96bddaa4f9c3ff2a04","imgThumUrl":"http://39.104.136.205:8888/car/20180907/1536326456841_small.jpg","imgUrl":"http://39.104.136.205:8888/car/20180907/1536326456841.jpg"}],"insuranceRebates":0,"loanRebates":0,"orderPrice":100,"outsiteColor":"黑","provinceCode":"370000","provinceName":"山东省","remark":"11","saleArea":"北京","saleCarPrice":1.01,"saleCommission":1,"shareNum":1,"shelvesNum":2,"typeName":"全国","userName":"经销商","vname":"2018款 30周年年型 Sportback 35 TFSI 进取型","withinColor":"黑"}
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
         * advicePrice : 100
         * brand : AC Schnitzer
         * browseNum : 12
         * carFormality : 不填
         * carId : 26d24330688e4b93bdeaa43563d6e259
         * carPrice : 1
         * carSeries : 奥迪RS 4
         * carSetting : 13
         * carStatusName : 已售
         * carType : 1
         * carUserName : 车源商负责人2
         * carYear : 2018
         * chassisNo : 123
         * cityCode : 370200
         * cityName : 青岛市
         * createDate : 1536759891000
         * customerName : 555233
         * discountContent : 1234
         * discounts : [{"discountName":"店内贷款"},{"discountName":"店内保养"}]
         * guidPrice : 2
         * imgs : [{"imgId":"4279cd5646e148b7b808b75d9ecd5c0c","imgThumUrl":"http://39.104.136.205:8888/car/20180907/1536326457482_small.jpg","imgUrl":"http://39.104.136.205:8888/car/20180907/1536326457482.jpg"},{"imgId":"553208bd8dad431e8f79f1a29f1f9cd8","imgThumUrl":"http://39.104.136.205:8888/car/20180907/1536326458325_small.jpg","imgUrl":"http://39.104.136.205:8888/car/20180907/1536326458325.jpg"},{"imgId":"b2002aca168b4c30951cf7deb03497f4","imgThumUrl":"http://39.104.136.205:8888/car/20180907/1536326458997_small.jpg","imgUrl":"http://39.104.136.205:8888/car/20180907/1536326458997.jpg"},{"imgId":"fbcd86b21da14d96bddaa4f9c3ff2a04","imgThumUrl":"http://39.104.136.205:8888/car/20180907/1536326456841_small.jpg","imgUrl":"http://39.104.136.205:8888/car/20180907/1536326456841.jpg"}]
         * insuranceRebates : 0
         * loanRebates : 0
         * orderPrice : 100
         * outsiteColor : 黑
         * provinceCode : 370000
         * provinceName : 山东省
         * remark : 11
         * saleArea : 北京
         * saleCarPrice : 1.01
         * saleCommission : 1
         * shareNum : 1
         * shelvesNum : 2
         * typeName : 全国
         * userName : 经销商
         * vname : 2018款 30周年年型 Sportback 35 TFSI 进取型
         * withinColor : 黑
         */

        private float advicePrice;
        private String brand;
        private int browseNum;
        private String carFormality;
        private String carId;
        private float carPrice;
        private String carSeries;
        private String carSetting;
        private String carStatusName;
        private String carType;
        private String carUserName;
        private String carYear;
        private String chassisNo;
        private String cityCode;
        private String cityName;
        private long createDate;
        private String customerName;
        private String discountContent;
        private float guidPrice;
        private int insuranceRebates;
        private int loanRebates;
        private float orderPrice;
        private String outsiteColor;
        private String provinceCode;
        private String provinceName;
        private String remark;
        private String saleArea;
        private double saleCarPrice;
        private int saleCommission;
        private int shareNum;
        private int shelvesNum;
        private String typeName;
        private String userName;
        private String vname;
        private String withinColor;
        private List<DiscountsBean> discounts;
        private List<ImgsBean> imgs;

        public float getAdvicePrice() {
            return advicePrice;
        }

        public void setAdvicePrice(float advicePrice) {
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

        public String getChassisNo() {
            return chassisNo;
        }

        public void setChassisNo(String chassisNo) {
            this.chassisNo = chassisNo;
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

        public String getCustomerName() {
            return customerName;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }

        public String getDiscountContent() {
            return discountContent;
        }

        public void setDiscountContent(String discountContent) {
            this.discountContent = discountContent;
        }

        public float getGuidPrice() {
            return guidPrice;
        }

        public void setGuidPrice(float guidPrice) {
            this.guidPrice = guidPrice;
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

        public float getOrderPrice() {
            return orderPrice;
        }

        public void setOrderPrice(float orderPrice) {
            this.orderPrice = orderPrice;
        }

        public String getOutsiteColor() {
            return outsiteColor;
        }

        public void setOutsiteColor(String outsiteColor) {
            this.outsiteColor = outsiteColor;
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

        public double getSaleCarPrice() {
            return saleCarPrice;
        }

        public void setSaleCarPrice(double saleCarPrice) {
            this.saleCarPrice = saleCarPrice;
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

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
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
             * discountName : 店内贷款
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
             * imgId : 4279cd5646e148b7b808b75d9ecd5c0c
             * imgThumUrl : http://39.104.136.205:8888/car/20180907/1536326457482_small.jpg
             * imgUrl : http://39.104.136.205:8888/car/20180907/1536326457482.jpg
             */

            private String imgId;
            private String imgThumUrl;
            private String imgUrl;

            public String getImgId() {
                return imgId;
            }

            public void setImgId(String imgId) {
                this.imgId = imgId;
            }

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
