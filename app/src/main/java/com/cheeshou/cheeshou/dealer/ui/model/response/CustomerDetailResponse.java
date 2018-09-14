package com.cheeshou.cheeshou.dealer.ui.model.response;

import java.io.Serializable;
import java.util.List;

public class CustomerDetailResponse implements Serializable {

    /**
     * code : 200
     * data : {"createDate":1533469561000,"customerId":"e943de9297e14b8c8fb7c626dc084b01","lists":[{"audiId":"10","audiName":"奥迪RS 4","brandId":"2","brandName":"AC Schnitzer","versionId":"1","versionName":"2018款 30周年年型 Sportback 35 TFSI 进取型"}],"maxBudget":50,"minBudget":25,"name":"6666","needTxt":"24234","phone":"33333","progresses":[{"content":"性别：男改成女","createDate":1535214449000,"source":"编辑","type":"无","userName":"经销商"},{"content":"修改客户意向车辆","createDate":1535178826000,"source":"编辑","type":"无","userName":"经销商"},{"content":"修改客户意向车辆","createDate":1535178819000,"source":"编辑","type":"无","userName":"经销商"},{"content":"修改客户意向车辆","createDate":1535178807000,"source":"编辑","type":"无","userName":"经销商"},{"content":"修改客户意向车辆","createDate":1535081928000,"source":"编辑","type":"无","userName":"经销商"},{"content":"修改客户意向车辆","createDate":1535081190000,"source":"编辑","type":"无","userName":"经销商"},{"content":"名称：555改成6666电话：555改成33333微信：55改成8888性别：女改成女","createDate":1534255191000,"source":"编辑","type":"无","userName":"销售"},{"content":"新建客户","createDate":1534255163000,"source":"新增","type":"电话","userName":"销售"}],"reserveColumn1":"否","saleCarInfos":[{"advicePrice":10,"brand":"AC Schnitzer","browseNum":50,"carId":"2343f712a10540698e6c5707a0720739","carPrice":2,"carSeries":"奥迪RS 4","carStatusName":"已预订","carUserName":"山东青岛","cityName":"合肥市","createDate":1535178826000,"guidPrice":3,"imgThumUrl":"http://39.104.136.205:8888/car/20180818/1534562444828_small.jpg","provinceName":"安徽省","saleCarPrice":222,"saleCommission":1,"saleId":"0659c7b50f83429b942f0fc74fb4ea8e","shareNum":2,"vname":"2018款 30周年年型 Sportback 35 TFSI 进取型"},{"advicePrice":10,"brand":"AC Schnitzer","browseNum":362,"carId":"a222f63ad3724db7a107172cf5e00e11","carPrice":2,"carSeries":"奥迪RS 4","carStatusName":"已上架","carUserName":"山东青岛","cityName":"合肥市","createDate":1535178826000,"guidPrice":3,"imgThumUrl":"http://39.104.136.205:8888/car/20180815/1534301691018_small.jpg","provinceName":"安徽省","saleCarPrice":90,"saleCommission":1,"saleId":"29b543fea6114082aac4eaaf7829a983","shareNum":0,"vname":"2018款 30周年年型 Sportback 35 TFSI 进取型"},{"advicePrice":10,"brand":"AC Schnitzer","browseNum":362,"carId":"b608f936e0f84f30a18977e4dbc3a8cc","carPrice":2,"carSeries":"奥迪RS 4","carStatusName":"已上架","carUserName":"山东青岛车源商3","cityName":"合肥市","createDate":1535178826000,"guidPrice":3,"imgThumUrl":"http://39.104.136.205:8888/car/20180815/1534304526428_small.jpg","provinceName":"安徽省","saleCarPrice":100,"saleCommission":1,"saleId":"7d1b0028258341539f3f1f2ec59e50a4","shareNum":0,"vname":"2018款 30周年年型 Sportback 35 TFSI 进取型"}],"sex":1,"status":"SUCCESS","statusName":"已成交","userName":"销售","weBcat":"8888"}
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

    public static class DataBean implements Serializable {
        /**
         * createDate : 1533469561000
         * customerId : e943de9297e14b8c8fb7c626dc084b01
         * lists : [{"audiId":"10","audiName":"奥迪RS 4","brandId":"2","brandName":"AC Schnitzer","versionId":"1","versionName":"2018款 30周年年型 Sportback 35 TFSI 进取型"}]
         * maxBudget : 50
         * minBudget : 25
         * name : 6666
         * needTxt : 24234
         * phone : 33333
         * progresses : [{"content":"性别：男改成女","createDate":1535214449000,"source":"编辑","type":"无","userName":"经销商"},{"content":"修改客户意向车辆","createDate":1535178826000,"source":"编辑","type":"无","userName":"经销商"},{"content":"修改客户意向车辆","createDate":1535178819000,"source":"编辑","type":"无","userName":"经销商"},{"content":"修改客户意向车辆","createDate":1535178807000,"source":"编辑","type":"无","userName":"经销商"},{"content":"修改客户意向车辆","createDate":1535081928000,"source":"编辑","type":"无","userName":"经销商"},{"content":"修改客户意向车辆","createDate":1535081190000,"source":"编辑","type":"无","userName":"经销商"},{"content":"名称：555改成6666电话：555改成33333微信：55改成8888性别：女改成女","createDate":1534255191000,"source":"编辑","type":"无","userName":"销售"},{"content":"新建客户","createDate":1534255163000,"source":"新增","type":"电话","userName":"销售"}]
         * reserveColumn1 : 否
         * saleCarInfos : [{"advicePrice":10,"brand":"AC Schnitzer","browseNum":50,"carId":"2343f712a10540698e6c5707a0720739","carPrice":2,"carSeries":"奥迪RS 4","carStatusName":"已预订","carUserName":"山东青岛","cityName":"合肥市","createDate":1535178826000,"guidPrice":3,"imgThumUrl":"http://39.104.136.205:8888/car/20180818/1534562444828_small.jpg","provinceName":"安徽省","saleCarPrice":222,"saleCommission":1,"saleId":"0659c7b50f83429b942f0fc74fb4ea8e","shareNum":2,"vname":"2018款 30周年年型 Sportback 35 TFSI 进取型"},{"advicePrice":10,"brand":"AC Schnitzer","browseNum":362,"carId":"a222f63ad3724db7a107172cf5e00e11","carPrice":2,"carSeries":"奥迪RS 4","carStatusName":"已上架","carUserName":"山东青岛","cityName":"合肥市","createDate":1535178826000,"guidPrice":3,"imgThumUrl":"http://39.104.136.205:8888/car/20180815/1534301691018_small.jpg","provinceName":"安徽省","saleCarPrice":90,"saleCommission":1,"saleId":"29b543fea6114082aac4eaaf7829a983","shareNum":0,"vname":"2018款 30周年年型 Sportback 35 TFSI 进取型"},{"advicePrice":10,"brand":"AC Schnitzer","browseNum":362,"carId":"b608f936e0f84f30a18977e4dbc3a8cc","carPrice":2,"carSeries":"奥迪RS 4","carStatusName":"已上架","carUserName":"山东青岛车源商3","cityName":"合肥市","createDate":1535178826000,"guidPrice":3,"imgThumUrl":"http://39.104.136.205:8888/car/20180815/1534304526428_small.jpg","provinceName":"安徽省","saleCarPrice":100,"saleCommission":1,"saleId":"7d1b0028258341539f3f1f2ec59e50a4","shareNum":0,"vname":"2018款 30周年年型 Sportback 35 TFSI 进取型"}]
         * sex : 1
         * status : SUCCESS
         * statusName : 已成交
         * userName : 销售
         * weBcat : 8888
         */

        private long createDate;
        private String customerId;
        private int maxBudget;
        private int minBudget;
        private String name;
        private String needTxt;
        private String phone;
        private String reserveColumn1;
        private int sex;
        private String status;
        private String statusName;
        private String userName;
        private String weBcat;
        private String remark;
        private List<ListsBean> lists;
        private List<ProgressesBean> progresses;
        private List<SaleCarInfosBean> saleCarInfos;

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public long getCreateDate() {
            return createDate;
        }

        public void setCreateDate(long createDate) {
            this.createDate = createDate;
        }

        public String getCustomerId() {
            return customerId;
        }

        public void setCustomerId(String customerId) {
            this.customerId = customerId;
        }

        public int getMaxBudget() {
            return maxBudget;
        }

        public void setMaxBudget(int maxBudget) {
            this.maxBudget = maxBudget;
        }

        public int getMinBudget() {
            return minBudget;
        }

        public void setMinBudget(int minBudget) {
            this.minBudget = minBudget;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNeedTxt() {
            return needTxt;
        }

        public void setNeedTxt(String needTxt) {
            this.needTxt = needTxt;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getReserveColumn1() {
            return reserveColumn1;
        }

        public void setReserveColumn1(String reserveColumn1) {
            this.reserveColumn1 = reserveColumn1;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getStatusName() {
            return statusName;
        }

        public void setStatusName(String statusName) {
            this.statusName = statusName;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getWeBcat() {
            return weBcat;
        }

        public void setWeBcat(String weBcat) {
            this.weBcat = weBcat;
        }

        public List<ListsBean> getLists() {
            return lists;
        }

        public void setLists(List<ListsBean> lists) {
            this.lists = lists;
        }

        public List<ProgressesBean> getProgresses() {
            return progresses;
        }

        public void setProgresses(List<ProgressesBean> progresses) {
            this.progresses = progresses;
        }

        public List<SaleCarInfosBean> getSaleCarInfos() {
            return saleCarInfos;
        }

        public void setSaleCarInfos(List<SaleCarInfosBean> saleCarInfos) {
            this.saleCarInfos = saleCarInfos;
        }

        public static class ListsBean implements Serializable {
            /**
             * audiId : 10
             * audiName : 奥迪RS 4
             * brandId : 2
             * brandName : AC Schnitzer
             * versionId : 1
             * versionName : 2018款 30周年年型 Sportback 35 TFSI 进取型
             */

            private String audiId;
            private String audiName;
            private String brandId;
            private String brandName;
            private String versionId;
            private String versionName;

            public String getAudiId() {
                return audiId;
            }

            public void setAudiId(String audiId) {
                this.audiId = audiId;
            }

            public String getAudiName() {
                return audiName;
            }

            public void setAudiName(String audiName) {
                this.audiName = audiName;
            }

            public String getBrandId() {
                return brandId;
            }

            public void setBrandId(String brandId) {
                this.brandId = brandId;
            }

            public String getBrandName() {
                return brandName;
            }

            public void setBrandName(String brandName) {
                this.brandName = brandName;
            }

            public String getVersionId() {
                return versionId;
            }

            public void setVersionId(String versionId) {
                this.versionId = versionId;
            }

            public String getVersionName() {
                return versionName;
            }

            public void setVersionName(String versionName) {
                this.versionName = versionName;
            }
        }

        public static class ProgressesBean implements Serializable {
            /**
             * content : 性别：男改成女
             * createDate : 1535214449000
             * source : 编辑
             * type : 无
             * userName : 经销商
             */

            private String content;
            private long createDate;
            private String source;
            private String type;
            private String userName;
            private List<ImageBean> imgs;

            public List<ImageBean> getImgs() {
                return imgs;
            }

            public void setImgs(List<ImageBean> imgs) {
                this.imgs = imgs;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public long getCreateDate() {
                return createDate;
            }

            public void setCreateDate(long createDate) {
                this.createDate = createDate;
            }

            public String getSource() {
                return source;
            }

            public void setSource(String source) {
                this.source = source;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }
        }


        public static class ImageBean implements Serializable {
            private String imgId;
            private String imgUrl;

            public String getImgId() {
                return imgId;
            }

            public void setImgId(String imgId) {
                this.imgId = imgId;
            }

            public String getImgUrl() {
                return imgUrl;
            }

            public void setImgUrl(String imgUrl) {
                this.imgUrl = imgUrl;
            }
        }

        public static class SaleCarInfosBean implements Serializable {
            /**
             * advicePrice : 10
             * brand : AC Schnitzer
             * browseNum : 50
             * carId : 2343f712a10540698e6c5707a0720739
             * carPrice : 2
             * carSeries : 奥迪RS 4
             * carStatusName : 已预订
             * carUserName : 山东青岛
             * cityName : 合肥市
             * createDate : 1535178826000
             * guidPrice : 3
             * imgThumUrl : http://39.104.136.205:8888/car/20180818/1534562444828_small.jpg
             * provinceName : 安徽省
             * saleCarPrice : 222
             * saleCommission : 1
             * saleId : 0659c7b50f83429b942f0fc74fb4ea8e
             * shareNum : 2
             * vname : 2018款 30周年年型 Sportback 35 TFSI 进取型
             */

            private int advicePrice;
            private String brand;
            private int browseNum;
            private String carId;
            private int carPrice;
            private String carSeries;
            private String carStatusName;
            private String carUserName;
            private String cityName;
            private long createDate;
            private int guidPrice;
            private String imgThumUrl;
            private String provinceName;
            private double saleCarPrice;
            private int saleCommission;
            private String saleId;
            private int shareNum;
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

            public String getVname() {
                return vname;
            }

            public void setVname(String vname) {
                this.vname = vname;
            }
        }
    }
}
