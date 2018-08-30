package com.cheeshou.cheeshou.options.model.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 71033 on 2018/8/5.
 */
public class CarDetailResponse implements Serializable{


    /**
     * code : 200
     * data : {"advicePrice":10,"audiId":1524,"brand":"奔驰","brandId":142,"browseNum":0,"carFormality":"手续全随车发","carId":"130598baa2f940f1b7a78407b7bea2a5","carPrice":50,"carSeries":"捷豹XE","carSetting":"配置非好","carStatusName":"已上架","carType":"1","carUserName":"车源商负责人2","carUserPhone":"13869647062","carYear":"2018","chassisNo":"272822","cityCode":"370200","cityName":"青岛市","createDate":1535599304000,"discountContent":"优惠信息","discounts":[{"discountName":"优惠信息"}],"guidPrice":59,"imgs":[{"imgId":"30980617a3be4abe93a3ded780ed98d5","imgThumUrl":"http://39.104.136.205:8888/car/20180830/1535599304448_small.jpg","imgUrl":"http://39.104.136.205:8888/car/20180830/1535599304448.jpg"},{"imgId":"33f03f49d2604840bcac03f291b151ac","imgThumUrl":"http://39.104.136.205:8888/car/20180830/1535599304479_small.jpg","imgUrl":"http://39.104.136.205:8888/car/20180830/1535599304479.jpg"},{"imgId":"b2c7985d4e7d4b8388baa3720640eda7","imgThumUrl":"http://39.104.136.205:8888/car/20180830/1535599304385_small.jpg","imgUrl":"http://39.104.136.205:8888/car/20180830/1535599304385.jpg"},{"imgId":"c93200ad282841729a02d558b0544e58","imgThumUrl":"http://39.104.136.205:8888/car/20180830/1535599304417_small.jpg","imgUrl":"http://39.104.136.205:8888/car/20180830/1535599304417.jpg"}],"insuranceRebates":4,"loanRebates":4,"outsiteColor":"黑","provinceCode":"370000","provinceName":"山东省","remark":"没有备注","saleArea":"华北","saleCarPrice":60,"saleCommission":4000,"saleId":"de233f406b5d4cbdaa71fe567d8b7bde","saleStatusName":"已上架","shareNum":0,"shelvesNum":1,"typeName":"全国","versionId":1361152,"vname":"2018款 2.0T 300PS 四驱R-Sport运动版","withinColor":"黑"}
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

    public static class DataBean implements Serializable{
        /**
         * advicePrice : 10
         * audiId : 1524
         * brand : 奔驰
         * brandId : 142
         * browseNum : 0
         * carFormality : 手续全随车发
         * carId : 130598baa2f940f1b7a78407b7bea2a5
         * carPrice : 50
         * carSeries : 捷豹XE
         * carSetting : 配置非好
         * carStatusName : 已上架
         * carType : 1
         * carUserName : 车源商负责人2
         * carUserPhone : 13869647062
         * carYear : 2018
         * chassisNo : 272822
         * cityCode : 370200
         * cityName : 青岛市
         * createDate : 1535599304000
         * discountContent : 优惠信息
         * discounts : [{"discountName":"优惠信息"}]
         * guidPrice : 59
         * imgs : [{"imgId":"30980617a3be4abe93a3ded780ed98d5","imgThumUrl":"http://39.104.136.205:8888/car/20180830/1535599304448_small.jpg","imgUrl":"http://39.104.136.205:8888/car/20180830/1535599304448.jpg"},{"imgId":"33f03f49d2604840bcac03f291b151ac","imgThumUrl":"http://39.104.136.205:8888/car/20180830/1535599304479_small.jpg","imgUrl":"http://39.104.136.205:8888/car/20180830/1535599304479.jpg"},{"imgId":"b2c7985d4e7d4b8388baa3720640eda7","imgThumUrl":"http://39.104.136.205:8888/car/20180830/1535599304385_small.jpg","imgUrl":"http://39.104.136.205:8888/car/20180830/1535599304385.jpg"},{"imgId":"c93200ad282841729a02d558b0544e58","imgThumUrl":"http://39.104.136.205:8888/car/20180830/1535599304417_small.jpg","imgUrl":"http://39.104.136.205:8888/car/20180830/1535599304417.jpg"}]
         * insuranceRebates : 4
         * loanRebates : 4
         * outsiteColor : 黑
         * provinceCode : 370000
         * provinceName : 山东省
         * remark : 没有备注
         * saleArea : 华北
         * saleCarPrice : 60
         * saleCommission : 4000
         * saleId : de233f406b5d4cbdaa71fe567d8b7bde
         * saleStatusName : 已上架
         * shareNum : 0
         * shelvesNum : 1
         * typeName : 全国
         * versionId : 1361152
         * vname : 2018款 2.0T 300PS 四驱R-Sport运动版
         * withinColor : 黑
         */

        private int advicePrice;
        private int audiId;
        private String brand;
        private int brandId;
        private int browseNum;
        private String carFormality;
        private String carId;
        private int carPrice;
        private String carSeries;
        private String carSetting;
        private String carStatusName;
        private String carType;
        private String carUserName;
        private String carUserPhone;
        private String carYear;
        private String chassisNo;
        private String cityCode;
        private String cityName;
        private long createDate;
        private String discountContent;
        private int guidPrice;
        private int insuranceRebates;
        private int loanRebates;
        private String outsiteColor;
        private String provinceCode;
        private String provinceName;
        private String remark;
        private String saleArea;
        private float saleCarPrice;
        private int saleCommission;
        private String saleId;
        private String saleStatusName;
        private int shareNum;
        private int shelvesNum;
        private String typeName;
        private int versionId;
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

        public int getAudiId() {
            return audiId;
        }

        public void setAudiId(int audiId) {
            this.audiId = audiId;
        }

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public int getBrandId() {
            return brandId;
        }

        public void setBrandId(int brandId) {
            this.brandId = brandId;
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

        public String getCarUserPhone() {
            return carUserPhone;
        }

        public void setCarUserPhone(String carUserPhone) {
            this.carUserPhone = carUserPhone;
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

        public String getSaleStatusName() {
            return saleStatusName;
        }

        public void setSaleStatusName(String saleStatusName) {
            this.saleStatusName = saleStatusName;
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

        public int getVersionId() {
            return versionId;
        }

        public void setVersionId(int versionId) {
            this.versionId = versionId;
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

        public static class DiscountsBean implements Serializable{
            /**
             * discountName : 优惠信息
             */

            private String discountName;

            public String getDiscountName() {
                return discountName;
            }

            public void setDiscountName(String discountName) {
                this.discountName = discountName;
            }
        }

        public static class ImgsBean implements Serializable{
            /**
             * imgId : 30980617a3be4abe93a3ded780ed98d5
             * imgThumUrl : http://39.104.136.205:8888/car/20180830/1535599304448_small.jpg
             * imgUrl : http://39.104.136.205:8888/car/20180830/1535599304448.jpg
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
