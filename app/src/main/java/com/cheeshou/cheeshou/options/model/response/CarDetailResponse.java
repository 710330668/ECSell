package com.cheeshou.cheeshou.options.model.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 71033 on 2018/8/5.
 */
public class CarDetailResponse implements Serializable{


    /**
     * code : 200
     * data : {"advicePrice":10,"audiId":10,"brand":"AC Schnitzer","brandId":2,"browseNum":34,"carFormality":"不填","carId":"8c777efacec2453789b867c06d580821","carPrice":2,"carSeries":"奥迪RS 4","carSetting":"hj","carStatusName":"已上架","carType":"1","carUserName":"山东青岛","carYear":"2018","cityCode":"340100","cityName":"合肥市","createDate":1534562562000,"discountContent":"hhh","discounts":[{"discountName":"店内贷款"},{"discountName":"店内保养"}],"guidPrice":3,"imgs":[{"imgThumUrl":"http://39.104.136.205:8888/car/20180818/1534562562844_small.jpg","imgUrl":"http://39.104.136.205:8888/car/20180818/1534562562844.jpg"},{"imgThumUrl":"http://39.104.136.205:8888/car/20180818/1534562561922_small.jpg","imgUrl":"http://39.104.136.205:8888/car/20180818/1534562561922.jpg"}],"insuranceRebates":12,"loanRebates":12,"outsiteColor":"红","provinceCode":"340000","provinceName":"安徽省","remark":"hhj","saleArea":"北京","saleCarPrice":2.2,"saleCommission":1,"saleId":"a4391258c8d14309b3069bd67275c86f","shareNum":0,"shelvesNum":1,"typeName":"全国","versionId":1,"vname":"2018款 30周年年型 Sportback 35 TFSI 进取型","withinColor":"红"}
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
         * audiId : 10
         * brand : AC Schnitzer
         * brandId : 2
         * browseNum : 34
         * carFormality : 不填
         * carId : 8c777efacec2453789b867c06d580821
         * carPrice : 2
         * carSeries : 奥迪RS 4
         * carSetting : hj
         * carStatusName : 已上架
         * carType : 1
         * carUserName : 山东青岛
         * carYear : 2018
         * cityCode : 340100
         * cityName : 合肥市
         * createDate : 1534562562000
         * discountContent : hhh
         * discounts : [{"discountName":"店内贷款"},{"discountName":"店内保养"}]
         * guidPrice : 3
         * imgs : [{"imgThumUrl":"http://39.104.136.205:8888/car/20180818/1534562562844_small.jpg","imgUrl":"http://39.104.136.205:8888/car/20180818/1534562562844.jpg"},{"imgThumUrl":"http://39.104.136.205:8888/car/20180818/1534562561922_small.jpg","imgUrl":"http://39.104.136.205:8888/car/20180818/1534562561922.jpg"}]
         * insuranceRebates : 12
         * loanRebates : 12
         * outsiteColor : 红
         * provinceCode : 340000
         * provinceName : 安徽省
         * remark : hhj
         * saleArea : 北京
         * saleCarPrice : 2.2
         * saleCommission : 1
         * saleId : a4391258c8d14309b3069bd67275c86f
         * shareNum : 0
         * shelvesNum : 1
         * typeName : 全国
         * versionId : 1
         * vname : 2018款 30周年年型 Sportback 35 TFSI 进取型
         * withinColor : 红
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
        private String carYear;
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
        private double saleCarPrice;
        private int saleCommission;
        private String saleId;
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

        public String getCarYear() {
            return carYear;
        }

        public void setCarYear(String carYear) {
            this.carYear = carYear;
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

        public static class ImgsBean implements Serializable{
            /**
             * imgThumUrl : http://39.104.136.205:8888/car/20180818/1534562562844_small.jpg
             * imgUrl : http://39.104.136.205:8888/car/20180818/1534562562844.jpg
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
