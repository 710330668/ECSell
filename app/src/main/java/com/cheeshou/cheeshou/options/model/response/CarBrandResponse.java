package com.cheeshou.cheeshou.options.model.response;

import java.util.List;

/**
 * Created by 71033 on 2018/8/3.
 */
public class CarBrandResponse {

    /**
     * code : 200
     * data : [{"addr":"AC SCHNITZER","brand":"AC Schnitzer","id":2,"initials":"A"},{"addr":"AGILE AUTOMOTIVE","brand":"Agile Automotive","id":3,"initials":"A"},{"addr":"ALPINA","brand":"ALPINA","id":4,"initials":"A"},{"addr":"APOLLO","brand":"Apollo","id":5,"initials":"A"},{"addr":"ARASH","brand":"Arash","id":6,"initials":"A"},{"addr":"ARCFOX","brand":"ARCFOX","id":7,"initials":"A"},{"addr":"ARIA","brand":"Aria","id":8,"initials":"A"},{"addr":"ATS","brand":"ATS","id":9,"initials":"A"},{"addr":"CATERHAM","brand":"Caterham","id":10,"initials":"C"}]
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
         * addr : AC SCHNITZER
         * brand : AC Schnitzer
         * id : 2
         * initials : A
         */

        private String addr;
        private String brand;
        private int id;
        private String initials;

        public String getAddr() {
            return addr;
        }

        public void setAddr(String addr) {
            this.addr = addr;
        }

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getInitials() {
            return initials;
        }

        public void setInitials(String initials) {
            this.initials = initials;
        }
    }
}
