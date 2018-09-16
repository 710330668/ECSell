package com.cheeshou.cheeshou.order.response;

import java.util.List;

/**
 * Created by 71033 on 2018/8/15.
 */
public class OrderListResponse {


    /**
     * code : 200
     * data : {"count":2,"lists":[{"brand":"Agile Automotive","carSeries":"奥迪A5","comDate":1534253657000,"customerName":"5552","imgThumUrl":"http://39.104.136.205:8888/20180729/car/1532859956333_small.jpg","orderItemId":"1","orderPrice":50,"userName":"销售","vname":"2017款 Sportback 45 TFSI quattro 运动型"},{"brand":"Agile Automotive","carSeries":"奥迪A5","customerName":"张晓光","imgThumUrl":"http://39.104.136.205:8888/20180729/car/1532859948243_small.jpg","orderItemId":"2","orderPrice":10,"userName":"销售","vname":"2017款 Sportback 45 TFSI quattro 运动型"}]}
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
         * count : 2
         * lists : [{"brand":"Agile Automotive","carSeries":"奥迪A5","comDate":1534253657000,"customerName":"5552","imgThumUrl":"http://39.104.136.205:8888/20180729/car/1532859956333_small.jpg","orderItemId":"1","orderPrice":50,"userName":"销售","vname":"2017款 Sportback 45 TFSI quattro 运动型"},{"brand":"Agile Automotive","carSeries":"奥迪A5","customerName":"张晓光","imgThumUrl":"http://39.104.136.205:8888/20180729/car/1532859948243_small.jpg","orderItemId":"2","orderPrice":10,"userName":"销售","vname":"2017款 Sportback 45 TFSI quattro 运动型"}]
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
             * brand : Agile Automotive
             * carSeries : 奥迪A5
             * comDate : 1534253657000
             * customerName : 5552
             * imgThumUrl : http://39.104.136.205:8888/20180729/car/1532859956333_small.jpg
             * orderItemId : 1
             * orderPrice : 50
             * userName : 销售
             * vname : 2017款 Sportback 45 TFSI quattro 运动型
             */

            private String brand;
            private String carSeries;
            private long comDate;
            private String customerName;
            private String imgThumUrl;
            private String orderItemId;
            private float orderPrice;
            private String userName;
            private String vname;

            public String getBrand() {
                return brand;
            }

            public void setBrand(String brand) {
                this.brand = brand;
            }

            public String getCarSeries() {
                return carSeries;
            }

            public void setCarSeries(String carSeries) {
                this.carSeries = carSeries;
            }

            public long getComDate() {
                return comDate;
            }

            public void setComDate(long comDate) {
                this.comDate = comDate;
            }

            public String getCustomerName() {
                return customerName;
            }

            public void setCustomerName(String customerName) {
                this.customerName = customerName;
            }

            public String getImgThumUrl() {
                return imgThumUrl;
            }

            public void setImgThumUrl(String imgThumUrl) {
                this.imgThumUrl = imgThumUrl;
            }

            public String getOrderItemId() {
                return orderItemId;
            }

            public void setOrderItemId(String orderItemId) {
                this.orderItemId = orderItemId;
            }

            public float getOrderPrice() {
                return orderPrice;
            }

            public void setOrderPrice(float orderPrice) {
                this.orderPrice = orderPrice;
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
        }
    }
}
