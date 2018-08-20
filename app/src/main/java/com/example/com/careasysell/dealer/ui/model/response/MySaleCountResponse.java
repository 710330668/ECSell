package com.example.com.careasysell.dealer.ui.model.response;

/**
 * Created by 71033 on 2018/8/20.
 */
public class MySaleCountResponse {


    /**
     * code : 200
     * data : {"saleCount":0,"saleMoney":0}
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
         * saleCount : 0
         * saleMoney : 0
         */

        private int saleCount;
        private int saleMoney;

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
