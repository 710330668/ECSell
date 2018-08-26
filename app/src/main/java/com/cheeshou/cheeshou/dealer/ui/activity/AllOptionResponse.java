package com.cheeshou.cheeshou.dealer.ui.activity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by 71033 on 2018/8/5.
 */
public class AllOptionResponse {


    /**
     * code : 200
     * data : {"count":14,"lists":[{"advicePrice":10,"brand":"奥迪","browseNum":0,"carId":"b93ed09073df42ada12e9ea7e58996e4","carPrice":33,"carSeries":"江淮iEV6E","carStatusName":"在售","carUserName":"山东青岛车源商3","carUserPhone":"18363671108","cityName":"衡阳市","createDate":1534665787000,"guidPrice":33,"provinceName":"湖南省","saleArea":"大西北","saleCommission":33,"shareNum":0,"shelvesNum":0,"typeName":"中规-期货","vname":"2017款 iEV6E"},{"advicePrice":10,"brand":"奥迪","browseNum":0,"carId":"84a999ad8d33411bad22368dae0a0a75","carPrice":222,"carSeries":"江淮iEV6E","carStatusName":"在售","carUserName":"山东青岛车源商3","carUserPhone":"18363671108","cityName":"徐州市","createDate":1534665140000,"guidPrice":222,"imgThumUrl":"http://39.104.136.205:8888/car/20180819/1534665140065_small.png","provinceName":"江苏省","saleArea":"大西北","saleCommission":2222,"shareNum":0,"shelvesNum":0,"typeName":"中规-期货","vname":"2017款 iEV6E"},{"advicePrice":10,"brand":"AC Schnitzer","browseNum":50,"carId":"8c777efacec2453789b867c06d580821","carPrice":2,"carSeries":"奥迪RS 4","carStatusName":"已上架","carUserName":"山东青岛","carUserPhone":"13869647062","cityName":"合肥市","createDate":1534562562000,"guidPrice":3,"imgThumUrl":"http://39.104.136.205:8888/car/20180818/1534562562844_small.jpg","provinceName":"安徽省","saleArea":"北京","saleCommission":1,"shareNum":0,"shelvesNum":1,"typeName":"全国","vname":"2018款 30周年年型 Sportback 35 TFSI 进取型"},{"advicePrice":10,"brand":"AC Schnitzer","browseNum":50,"carId":"2343f712a10540698e6c5707a0720739","carPrice":2,"carSeries":"奥迪RS 4","carStatusName":"已上架","carUserName":"山东青岛","carUserPhone":"13869647062","cityName":"合肥市","createDate":1534562444000,"guidPrice":3,"imgThumUrl":"http://39.104.136.205:8888/car/20180818/1534562444828_small.jpg","provinceName":"安徽省","saleArea":"北京","saleCommission":1,"shareNum":2,"shelvesNum":1,"typeName":"全国","vname":"2018款 30周年年型 Sportback 35 TFSI 进取型"},{"advicePrice":10,"brand":"宝马1","browseNum":0,"carId":"0e7eae99432947afa2326a6c406bf56d","carPrice":55,"carSeries":"科迈罗","carStatusName":"在售","carUserName":"山东青岛","carUserPhone":"13869647062","cityName":"青岛市","createDate":1534506716000,"guidPrice":66,"imgThumUrl":"http://39.104.136.205:8888/car/20180820/1534774815092_small.jpg","provinceName":"山东省","saleArea":"华北","saleCommis     sion":3000,"shareNum":0,"shelvesNum":0,"typeName":"全国","vname":"2017款 2.0T RS"},{"advicePrice":10,"brand":"AC Schnitzer","browseNum":362,"carId":"b608f936e0f84f30a18977e4dbc3a8cc","carPrice":2,"carSeries":"奥迪RS 4","carStatusName":"已上架","carUserName":"山东青岛车源商3","carUserPhone":"18363671108","cityName":"合肥市","createDate":1534429062000,"guidPrice":3,"imgThumUrl":"http://39.104.136.205:8888/car/20180815/1534304524776_small.jpg","provinceName":"安徽省","saleArea":"北京","saleCommission":1,"shareNum":0,"shelvesNum":1,"typeName":"全国","vname":"2018款 30周年年型 Sportback 35 TFSI 进取型"},{"advicePrice":10,"brand":"AC Schnitzer","browseNum":362,"carId":"a222f63ad3724db7a107172cf5e00e11","carPrice":2,"carSeries":"奥迪RS 4","carStatusName":"已上架","carUserName":"山东青岛","carUserPhone":"13869647062","cityName":"合肥市","createDate":1534301691000,"guidPrice":3,"imgThumUrl":"http://39.104.136.205:8888/car/20180815/1534301691018_small.jpg","provinceName":"安徽省","saleArea":"北京","saleCommission":1,"shareNum":0,"shelvesNum":1,"typeName":"全国","vname":"2018款 30周年年型 Sportback 35 TFSI 进取型"},{"advicePrice":10,"brand":"AC Schnitzer","browseNum":0,"carId":"52f0d702a90b41b6b9f6a127f7ab4033","carPrice":2,"carSeries":"奥迪RS 4","carStatusName":"在售","carUserName":"山东青岛","carUserPhone":"13869647062","cityName":"合肥市","createDate":1534301650000,"guidPrice":3,"imgThumUrl":"http://39.104.136.205:8888/car/20180815/1534301650054_small.jpg","provinceName":"安徽省","saleArea":"北京","saleCommission":1,"shareNum":0,"shelvesNum":0,"typeName":"全国","vname":"2018款 30周年年型 Sportback 35 TFSI 进取型"},{"advicePrice":10,"brand":"AC Schnitzer","browseNum":426,"carId":"aafad4776f0b47aba262e643c1f14862","carPrice":120,"carSeries":"奥迪RS 4","carStatusName":"已上架","carUserName":"山东青岛","carUserPhone":"13869647062","cityName":"蚌埠市","createDate":1533820012000,"guidPrice":240,"imgThumUrl":"http://39.104.136.205:8888/car/20180825/1535185992248_small.png","provinceName":"安徽省","saleArea":"华北","saleCommission":2000,"shareNum":7,"shelvesNum":1,"typeName":"国产-现车","vname":"2018款 30周年年型 Sportback 40 TFSI 运动型"},{"advicePrice":10,"brand":"Agile Automotive","browseNum":433,"carId":"0d76e55458a644d49dc79f3a46f069d5","carPrice":123,"carSeries":"奥迪A5","carStatusName":"已上架","carUserName":"山东青岛","carUserPhone":"13869647062","cityName":"蚌埠市","createDate":1532859956000,"guidPrice":240,"imgThumUrl":"http://39.104.136.205:8888/20180729/car/1532859956333_small.jpg","provinceName":"安徽省","saleArea":"华北","saleCommission":3,"shareNum":12,"shelvesNum":3,"typeName":"国产-现车","vname":"2017款 Sportback 45 TFSI quattro 运动型"},{"advicePrice":10,"brand":"Agile Automotive","bro     wseNum":433,"carId":"0a6a1f00182548cebfa2f4f5b8a375f1","carPrice":130,"carSeries":"奥迪A5","carStatusName":"已预订","carUserName":"山东青岛","carUserPhone":"13869647062","cityName":"蚌埠市","createDate":1532859948000,"guidPrice":240,"imgThumUrl":"http://39.104.136.205:8888/car/20180815/1534304526428_small.jpg","provinceName":"安徽省","saleArea":"华北","saleCommission":2,"shareNum":8,"shelvesNum":3,"typeName":"全国","vname":"2017款 Sportback 45 TFSI quattro 运动型"},{"advicePrice":10,"brand":"Agile Automotive","browseNum":0,"carId":"bc85396b98b94e5a86d2445f81168f56","carPrice":126,"carSeries":"奥迪A5","carStatusName":"在售","carUserName":"山东青岛","carUserPhone":"13869647062","cityName":"蚌埠市","createDate":1532859936000,"guidPrice":240,"imgThumUrl":"http://39.104.136.205:8888/20180729/car/1532859935683_small.jpg","provinceName":"安徽省","saleArea":"华北","saleCommission":6,"shareNum":0,"shelvesNum":0,"typeName":"国产-现车","vname":"2017款 Sportback 45 TFSI quattro 运动型"}]}
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
         * count : 14
         * lists : [{"advicePrice":10,"brand":"奥迪","browseNum":0,"carId":"b93ed09073df42ada12e9ea7e58996e4","carPrice":33,"carSeries":"江淮iEV6E","carStatusName":"在售","carUserName":"山东青岛车源商3","carUserPhone":"18363671108","cityName":"衡阳市","createDate":1534665787000,"guidPrice":33,"provinceName":"湖南省","saleArea":"大西北","saleCommission":33,"shareNum":0,"shelvesNum":0,"typeName":"中规-期货","vname":"2017款 iEV6E"},{"advicePrice":10,"brand":"奥迪","browseNum":0,"carId":"84a999ad8d33411bad22368dae0a0a75","carPrice":222,"carSeries":"江淮iEV6E","carStatusName":"在售","carUserName":"山东青岛车源商3","carUserPhone":"18363671108","cityName":"徐州市","createDate":1534665140000,"guidPrice":222,"imgThumUrl":"http://39.104.136.205:8888/car/20180819/1534665140065_small.png","provinceName":"江苏省","saleArea":"大西北","saleCommission":2222,"shareNum":0,"shelvesNum":0,"typeName":"中规-期货","vname":"2017款 iEV6E"},{"advicePrice":10,"brand":"AC Schnitzer","browseNum":50,"carId":"8c777efacec2453789b867c06d580821","carPrice":2,"carSeries":"奥迪RS 4","carStatusName":"已上架","carUserName":"山东青岛","carUserPhone":"13869647062","cityName":"合肥市","createDate":1534562562000,"guidPrice":3,"imgThumUrl":"http://39.104.136.205:8888/car/20180818/1534562562844_small.jpg","provinceName":"安徽省","saleArea":"北京","saleCommission":1,"shareNum":0,"shelvesNum":1,"typeName":"全国","vname":"2018款 30周年年型 Sportback 35 TFSI 进取型"},{"advicePrice":10,"brand":"AC Schnitzer","browseNum":50,"carId":"2343f712a10540698e6c5707a0720739","carPrice":2,"carSeries":"奥迪RS 4","carStatusName":"已上架","carUserName":"山东青岛","carUserPhone":"13869647062","cityName":"合肥市","createDate":1534562444000,"guidPrice":3,"imgThumUrl":"http://39.104.136.205:8888/car/20180818/1534562444828_small.jpg","provinceName":"安徽省","saleArea":"北京","saleCommission":1,"shareNum":2,"shelvesNum":1,"typeName":"全国","vname":"2018款 30周年年型 Sportback 35 TFSI 进取型"},{"advicePrice":10,"brand":"宝马1","browseNum":0,"carId":"0e7eae99432947afa2326a6c406bf56d","carPrice":55,"carSeries":"科迈罗","carStatusName":"在售","carUserName":"山东青岛","carUserPhone":"13869647062","cityName":"青岛市","createDate":1534506716000,"guidPrice":66,"imgThumUrl":"http://39.104.136.205:8888/car/20180820/1534774815092_small.jpg","provinceName":"山东省","saleArea":"华北","saleCommis     sion":3000,"shareNum":0,"shelvesNum":0,"typeName":"全国","vname":"2017款 2.0T RS"},{"advicePrice":10,"brand":"AC Schnitzer","browseNum":362,"carId":"b608f936e0f84f30a18977e4dbc3a8cc","carPrice":2,"carSeries":"奥迪RS 4","carStatusName":"已上架","carUserName":"山东青岛车源商3","carUserPhone":"18363671108","cityName":"合肥市","createDate":1534429062000,"guidPrice":3,"imgThumUrl":"http://39.104.136.205:8888/car/20180815/1534304524776_small.jpg","provinceName":"安徽省","saleArea":"北京","saleCommission":1,"shareNum":0,"shelvesNum":1,"typeName":"全国","vname":"2018款 30周年年型 Sportback 35 TFSI 进取型"},{"advicePrice":10,"brand":"AC Schnitzer","browseNum":362,"carId":"a222f63ad3724db7a107172cf5e00e11","carPrice":2,"carSeries":"奥迪RS 4","carStatusName":"已上架","carUserName":"山东青岛","carUserPhone":"13869647062","cityName":"合肥市","createDate":1534301691000,"guidPrice":3,"imgThumUrl":"http://39.104.136.205:8888/car/20180815/1534301691018_small.jpg","provinceName":"安徽省","saleArea":"北京","saleCommission":1,"shareNum":0,"shelvesNum":1,"typeName":"全国","vname":"2018款 30周年年型 Sportback 35 TFSI 进取型"},{"advicePrice":10,"brand":"AC Schnitzer","browseNum":0,"carId":"52f0d702a90b41b6b9f6a127f7ab4033","carPrice":2,"carSeries":"奥迪RS 4","carStatusName":"在售","carUserName":"山东青岛","carUserPhone":"13869647062","cityName":"合肥市","createDate":1534301650000,"guidPrice":3,"imgThumUrl":"http://39.104.136.205:8888/car/20180815/1534301650054_small.jpg","provinceName":"安徽省","saleArea":"北京","saleCommission":1,"shareNum":0,"shelvesNum":0,"typeName":"全国","vname":"2018款 30周年年型 Sportback 35 TFSI 进取型"},{"advicePrice":10,"brand":"AC Schnitzer","browseNum":426,"carId":"aafad4776f0b47aba262e643c1f14862","carPrice":120,"carSeries":"奥迪RS 4","carStatusName":"已上架","carUserName":"山东青岛","carUserPhone":"13869647062","cityName":"蚌埠市","createDate":1533820012000,"guidPrice":240,"imgThumUrl":"http://39.104.136.205:8888/car/20180825/1535185992248_small.png","provinceName":"安徽省","saleArea":"华北","saleCommission":2000,"shareNum":7,"shelvesNum":1,"typeName":"国产-现车","vname":"2018款 30周年年型 Sportback 40 TFSI 运动型"},{"advicePrice":10,"brand":"Agile Automotive","browseNum":433,"carId":"0d76e55458a644d49dc79f3a46f069d5","carPrice":123,"carSeries":"奥迪A5","carStatusName":"已上架","carUserName":"山东青岛","carUserPhone":"13869647062","cityName":"蚌埠市","createDate":1532859956000,"guidPrice":240,"imgThumUrl":"http://39.104.136.205:8888/20180729/car/1532859956333_small.jpg","provinceName":"安徽省","saleArea":"华北","saleCommission":3,"shareNum":12,"shelvesNum":3,"typeName":"国产-现车","vname":"2017款 Sportback 45 TFSI quattro 运动型"},{"advicePrice":10,"brand":"Agile Automotive","bro     wseNum":433,"carId":"0a6a1f00182548cebfa2f4f5b8a375f1","carPrice":130,"carSeries":"奥迪A5","carStatusName":"已预订","carUserName":"山东青岛","carUserPhone":"13869647062","cityName":"蚌埠市","createDate":1532859948000,"guidPrice":240,"imgThumUrl":"http://39.104.136.205:8888/car/20180815/1534304526428_small.jpg","provinceName":"安徽省","saleArea":"华北","saleCommission":2,"shareNum":8,"shelvesNum":3,"typeName":"全国","vname":"2017款 Sportback 45 TFSI quattro 运动型"},{"advicePrice":10,"brand":"Agile Automotive","browseNum":0,"carId":"bc85396b98b94e5a86d2445f81168f56","carPrice":126,"carSeries":"奥迪A5","carStatusName":"在售","carUserName":"山东青岛","carUserPhone":"13869647062","cityName":"蚌埠市","createDate":1532859936000,"guidPrice":240,"imgThumUrl":"http://39.104.136.205:8888/20180729/car/1532859935683_small.jpg","provinceName":"安徽省","saleArea":"华北","saleCommission":6,"shareNum":0,"shelvesNum":0,"typeName":"国产-现车","vname":"2017款 Sportback 45 TFSI quattro 运动型"}]
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
             * brand : 奥迪
             * browseNum : 0
             * carId : b93ed09073df42ada12e9ea7e58996e4
             * carPrice : 33
             * carSeries : 江淮iEV6E
             * carStatusName : 在售
             * carUserName : 山东青岛车源商3
             * carUserPhone : 18363671108
             * cityName : 衡阳市
             * createDate : 1534665787000
             * guidPrice : 33
             * provinceName : 湖南省
             * saleArea : 大西北
             * saleCommission : 33
             * shareNum : 0
             * shelvesNum : 0
             * typeName : 中规-期货
             * vname : 2017款 iEV6E
             * imgThumUrl : http://39.104.136.205:8888/car/20180819/1534665140065_small.png
             * saleCommis     sion : 3000
             * bro     wseNum : 433
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
            private String provinceName;
            private String saleArea;
            private int saleCommission;
            private int shareNum;
            private int shelvesNum;
            private String typeName;
            private String vname;
            private String imgThumUrl;
            @SerializedName("saleCommis     sion")
            private int _$SaleCommisSion76; // FIXME check this code
            @SerializedName("bro     wseNum")
            private int _$BroWseNum75; // FIXME check this code

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

            public String getImgThumUrl() {
                return imgThumUrl;
            }

            public void setImgThumUrl(String imgThumUrl) {
                this.imgThumUrl = imgThumUrl;
            }

            public int get_$SaleCommisSion76() {
                return _$SaleCommisSion76;
            }

            public void set_$SaleCommisSion76(int _$SaleCommisSion76) {
                this._$SaleCommisSion76 = _$SaleCommisSion76;
            }

            public int get_$BroWseNum75() {
                return _$BroWseNum75;
            }

            public void set_$BroWseNum75(int _$BroWseNum75) {
                this._$BroWseNum75 = _$BroWseNum75;
            }
        }
    }
}
