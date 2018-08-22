package com.cheeshou.cheeshou.dealer.ui.model.response;

/**
 * Created by 71033 on 2018/8/18.
 */
public class MyReportResponse {


    /**
     * code : 200
     * data : {"dayCustomerCount":0,"dayEnterCount":0,"daySaleCount":0,"monthCustomerCount":15,"monthEnterCount":13,"monthSaleCount":2}
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
         * dayCustomerCount : 0
         * dayEnterCount : 0
         * daySaleCount : 0
         * monthCustomerCount : 15
         * monthEnterCount : 13
         * monthSaleCount : 2
         */

        private int dayCustomerCount;
        private int dayEnterCount;
        private int daySaleCount;
        private int monthCustomerCount;
        private int monthEnterCount;
        private int monthSaleCount;

        public int getDayCustomerCount() {
            return dayCustomerCount;
        }

        public void setDayCustomerCount(int dayCustomerCount) {
            this.dayCustomerCount = dayCustomerCount;
        }

        public int getDayEnterCount() {
            return dayEnterCount;
        }

        public void setDayEnterCount(int dayEnterCount) {
            this.dayEnterCount = dayEnterCount;
        }

        public int getDaySaleCount() {
            return daySaleCount;
        }

        public void setDaySaleCount(int daySaleCount) {
            this.daySaleCount = daySaleCount;
        }

        public int getMonthCustomerCount() {
            return monthCustomerCount;
        }

        public void setMonthCustomerCount(int monthCustomerCount) {
            this.monthCustomerCount = monthCustomerCount;
        }

        public int getMonthEnterCount() {
            return monthEnterCount;
        }

        public void setMonthEnterCount(int monthEnterCount) {
            this.monthEnterCount = monthEnterCount;
        }

        public int getMonthSaleCount() {
            return monthSaleCount;
        }

        public void setMonthSaleCount(int monthSaleCount) {
            this.monthSaleCount = monthSaleCount;
        }
    }
}
