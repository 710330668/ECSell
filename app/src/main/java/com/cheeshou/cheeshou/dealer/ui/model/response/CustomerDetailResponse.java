package com.cheeshou.cheeshou.dealer.ui.model.response;

import java.util.ArrayList;
import java.util.List;

public class CustomerDetailResponse {

    /**
     * code : 200
     * data : {"customerId":"57e7e659bde84467a821b1bffc4c51b0","lists":[],"maxBudget":30,"minBudget":20,"name":"dddkk","needTxt":"dd","phone":"13555555555","progresses":[{"content":"性别：女改成男","createDate":1534690650000,"source":"编辑","type":"无"},{"content":"","createDate":1534405097000,"source":"编辑","type":"无"},{"content":"名称：kk改成dddkk性别：男改成女","createDate":1534405089000,"source":"编辑","type":"无"},{"content":"Daodian","createDate":1534401274000,"source":"跟进","type":"到店"},{"content":"新建客户","createDate":1534399919000,"source":"新增","type":"短信"}],"remark":"","saleCarInfos":[],"sex":0,"status":"YES_STORE","statusName":"已到店","userName":"经销商","weBcat":"dc25363266"}
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
         * customerId : 57e7e659bde84467a821b1bffc4c51b0
         * lists : []
         * maxBudget : 30
         * minBudget : 20
         * name : dddkk
         * needTxt : dd
         * phone : 13555555555
         * progresses : [{"content":"性别：女改成男","createDate":1534690650000,"source":"编辑","type":"无"},{"content":"","createDate":1534405097000,"source":"编辑","type":"无"},{"content":"名称：kk改成dddkk性别：男改成女","createDate":1534405089000,"source":"编辑","type":"无"},{"content":"Daodian","createDate":1534401274000,"source":"跟进","type":"到店"},{"content":"新建客户","createDate":1534399919000,"source":"新增","type":"短信"}]
         * remark :
         * saleCarInfos : []
         * sex : 0
         * status : YES_STORE
         * statusName : 已到店
         * userName : 经销商
         * weBcat : dc25363266
         */

        private String customerId;
        private int maxBudget;
        private int minBudget;
        private String name;
        private String needTxt;
        private String phone;
        private String remark;
        private int sex;
        private String status;
        private String statusName;
        private String userName;
        private String weBcat;
        private List<String> lists;
        private List<ProgressesBean> progresses;
        private List<SaleCarInfoBean> saleCarInfos;

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

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
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

        public List<String> getLists() {
            return lists;
        }

        public void setLists(List<String> lists) {
            this.lists = lists;
        }

        public List<ProgressesBean> getProgresses() {
            if (progresses == null) {
                progresses = new ArrayList<>();
            }
            return progresses;
        }

        public void setProgresses(List<ProgressesBean> progresses) {
            this.progresses = progresses;
        }

        public List<SaleCarInfoBean> getSaleCarInfos() {
            if (saleCarInfos == null) {
                saleCarInfos = new ArrayList<>();
            }
            return saleCarInfos;
        }

        public void setSaleCarInfos(List<SaleCarInfoBean> saleCarInfos) {
            this.saleCarInfos = saleCarInfos;
        }

        public static class ProgressesBean {
            /**
             * content : 性别：女改成男
             * createDate : 1534690650000
             * source : 编辑
             * type : 无
             */

            private String content;
            private long createDate;
            private String source;
            private String type;

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
        }

        public static class SaleCarInfoBean {
            //            brand：品牌
//            carId：车辆id
//            carPrice：价格
//            carSeries：系列
//            carStatusName：状态名
//            carUserName：车源商
//            cityName：城市名称
//            guidPrice：指导价
//            imgThumUrl：缩略图
//            provinceName：省份
//            saleCommission：销售提成
//            vname：型号
            private String brand;
            private String carId;
            private String carPrice;
            private String carSeries;
            private String carStatusName;
            private String carUserName;
            private String cityName;
            private String guidPrice;
            private String imgThumUrl;
            private String provinceName;
            private String saleCommission;
            private String vname;

            public String getBrand() {
                return brand;
            }

            public void setBrand(String brand) {
                this.brand = brand;
            }

            public String getCarId() {
                return carId;
            }

            public void setCarId(String carId) {
                this.carId = carId;
            }

            public String getCarPrice() {
                return carPrice;
            }

            public void setCarPrice(String carPrice) {
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

            public String getGuidPrice() {
                return guidPrice;
            }

            public void setGuidPrice(String guidPrice) {
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

            public String getSaleCommission() {
                return saleCommission;
            }

            public void setSaleCommission(String saleCommission) {
                this.saleCommission = saleCommission;
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
