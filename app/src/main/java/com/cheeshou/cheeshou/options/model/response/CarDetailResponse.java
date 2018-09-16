package com.cheeshou.cheeshou.options.model.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 71033 on 2018/8/5.
 */
public class CarDetailResponse implements Serializable{


    /**
     * code : 200
     * data : {"advicePrice":10,"audiId":1541,"brand":"奥迪","brandId":143,"browseNum":181,"carFormality":"商检","carId":"44db4f00fb044aaa9cc79939552c1435","carPrice":49,"carSeries":"江淮iEV6E","carSetting":"peizhi","carStatusName":"已上架","carType":"1","carUserName":"yang","carUserPhone":"18661835353","carYear":"2018","chassisNo":"27288373","cityCode":"370200","cityName":"青岛市","createDate":1535763538000,"discountContent":"斤斤计较","discounts":[{"discountName":"斤斤计较"}],"guidPrice":66,"imgs":[{"imgId":"1dc92f249c844ae1bc9ec347017727af","imgThumUrl":"http://39.104.136.205:8888/car/20180901/1535763538607_small.jpg","imgUrl":"http://39.104.136.205:8888/car/20180901/1535763538607.jpg"},{"imgId":"1f7c35b1a6a344f5a6e83fedb69ccb5b","imgThumUrl":"http://39.104.136.205:8888/car/20180901/1535763538560_small.jpg","imgUrl":"http://39.104.136.205:8888/car/20180901/1535763538560.jpg"},{"imgId":"392013101fe749a29911201893219581","imgThumUrl":"http://39.104.136.205:8888/car/20180901/1535763538639_small.jpg","imgUrl":"http://39.104.136.205:8888/car/20180901/1535763538639.jpg"}],"insuranceRebates":3,"loanRebates":3,"outsiteColor":"黑","provinceCode":"370000","provinceName":"山东省","remark":"备注","saleArea":"华北","saleCarPrice":53.9,"saleCommission":5000,"saleId":"9b70d93e49a04821807038f535fe5fbb","saleStatusName":"已上架","shareNum":21,"shelvesNum":2,"typeName":"全国","versionId":1361831,"vname":"2017款 iEV6E","withinColor":"红"}
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
         * audiId : 1541
         * brand : 奥迪
         * brandId : 143
         * browseNum : 181
         * carFormality : 商检
         * carId : 44db4f00fb044aaa9cc79939552c1435
         * carPrice : 49
         * carSeries : 江淮iEV6E
         * carSetting : peizhi
         * carStatusName : 已上架
         * carType : 1
         * carUserName : yang
         * carUserPhone : 18661835353
         * carYear : 2018
         * chassisNo : 27288373
         * cityCode : 370200
         * cityName : 青岛市
         * createDate : 1535763538000
         * discountContent : 斤斤计较
         * discounts : [{"discountName":"斤斤计较"}]
         * guidPrice : 66
         * imgs : [{"imgId":"1dc92f249c844ae1bc9ec347017727af","imgThumUrl":"http://39.104.136.205:8888/car/20180901/1535763538607_small.jpg","imgUrl":"http://39.104.136.205:8888/car/20180901/1535763538607.jpg"},{"imgId":"1f7c35b1a6a344f5a6e83fedb69ccb5b","imgThumUrl":"http://39.104.136.205:8888/car/20180901/1535763538560_small.jpg","imgUrl":"http://39.104.136.205:8888/car/20180901/1535763538560.jpg"},{"imgId":"392013101fe749a29911201893219581","imgThumUrl":"http://39.104.136.205:8888/car/20180901/1535763538639_small.jpg","imgUrl":"http://39.104.136.205:8888/car/20180901/1535763538639.jpg"}]
         * insuranceRebates : 3
         * loanRebates : 3
         * outsiteColor : 黑
         * provinceCode : 370000
         * provinceName : 山东省
         * remark : 备注
         * saleArea : 华北
         * saleCarPrice : 53.9
         * saleCommission : 5000
         * saleId : 9b70d93e49a04821807038f535fe5fbb
         * saleStatusName : 已上架
         * shareNum : 21
         * shelvesNum : 2
         * typeName : 全国
         * versionId : 1361831
         * vname : 2017款 iEV6E
         * withinColor : 红
         */

        private float advicePrice;
        private int audiId;
        private String brand;
        private int brandId;
        private int browseNum;
        private String carFormality;
        private String carId;
        private float carPrice;
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
        private float guidPrice;
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
        private String saleStatusName;
        private int shareNum;
        private int shelvesNum;
        private String typeName;
        private int versionId;
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
             * discountName : 斤斤计较
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
             * imgId : 1dc92f249c844ae1bc9ec347017727af
             * imgThumUrl : http://39.104.136.205:8888/car/20180901/1535763538607_small.jpg
             * imgUrl : http://39.104.136.205:8888/car/20180901/1535763538607.jpg
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
