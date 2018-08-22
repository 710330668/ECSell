package com.cheeshou.cheeshou.options.model.response;

import java.util.List;

/**
 * Created by 71033 on 2018/8/18.
 */
public class HotCarListResponse {


    /**
     * code : 200
     * data : {"count":1,"lists":[{"advicePrice":10,"brand":"AC Schnitzer","browseNum":376,"carId":"aafad4776f0b47aba262e643c1f14862","carSeries":"奥迪RS 4","createDate":1533820012000,"shareNum":0,"shelvesNum":1,"vname":"2018款 30周年年型 Sportback 40 TFSI 运动型"}]}
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
         * lists : [{"advicePrice":10,"brand":"AC Schnitzer","browseNum":376,"carId":"aafad4776f0b47aba262e643c1f14862","carSeries":"奥迪RS 4","createDate":1533820012000,"shareNum":0,"shelvesNum":1,"vname":"2018款 30周年年型 Sportback 40 TFSI 运动型"}]
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
             * brand : AC Schnitzer
             * browseNum : 376
             * carId : aafad4776f0b47aba262e643c1f14862
             * carSeries : 奥迪RS 4
             * createDate : 1533820012000
             * shareNum : 0
             * shelvesNum : 1
             * vname : 2018款 30周年年型 Sportback 40 TFSI 运动型
             */

            private int advicePrice;
            private String brand;
            private int browseNum;
            private String carId;
            private String carSeries;
            private long createDate;
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

            public String getCarSeries() {
                return carSeries;
            }

            public void setCarSeries(String carSeries) {
                this.carSeries = carSeries;
            }

            public long getCreateDate() {
                return createDate;
            }

            public void setCreateDate(long createDate) {
                this.createDate = createDate;
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
