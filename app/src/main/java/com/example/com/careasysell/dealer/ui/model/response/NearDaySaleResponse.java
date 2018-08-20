package com.example.com.careasysell.dealer.ui.model.response;

import java.util.List;

/**
 * Created by 71033 on 2018/8/20.
 */
public class NearDaySaleResponse {


    /**
     * code : 200
     * data : [{"createDate":"2018-08-11","saleCount":3,"saleMoney":60},{"createDate":"2018-08-16","saleCount":1,"saleMoney":200}]
     * msg : 操作成功
     */

    private int code;
    private String msg;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * createDate : 2018-08-11
         * saleCount : 3
         * saleMoney : 60
         */

        private String createDate;
        private int saleCount;
        private int saleMoney;

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public int getSaleCount() {
            return saleCount;
        }

        public void setSaleCount(int saleCount) {
            this.saleCount = saleCount;
        }

        public int getSaleMoney() {
            return saleMoney;
        }

        public void setSaleMoney(int saleMoney) {
            this.saleMoney = saleMoney;
        }
    }
}
