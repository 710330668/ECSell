package com.cheeshou.cheeshou.options.model.response;

import java.util.List;

/**
 * Created by 71033 on 2018/8/4.
 */
public class CarsModelResponse {


    /**
     * code : 200
     * data : [{"id":1,"vName":"2018款 30周年年型 Sportback 35 TFSI 进取型","version":"1.4升 涡轮增压 150马力"},{"id":2,"vName":"2018款 30周年年型 Sportback 35 TFSI 时尚型","version":"1.4升 涡轮增压 150马力"},{"id":3,"vName":"2018款 30周年年型 Sportback 35 TFSI 风尚型","version":"1.4升 涡轮增压 150马力"},{"id":4,"vName":"2018款 30周年年型 Sportback 35 TFSI 运动型","version":"1.4升 涡轮增压 150马力"},{"id":5,"vName":"2018款 30周年年型 Limousine 35 TFSI 进取型","version":"1.4升 涡轮增压 150马力"},{"id":6,"vName":"2018款 30周年年型 Limousine 35 TFSI 时尚型","version":"1.4升 涡轮增压 150马力"},{"id":7,"vName":"2018款 30周年年型 Limousine 35 TFSI 风尚型","version":"1.4升 涡轮增压 150马力"},{"id":8,"vName":"2018款 30周年年型 Limousine 35 TFSI 运动型","version":"1.4升 涡轮增压 150马力"},{"id":9,"vName":"2018款 30周年年型 Sportback 40 TFSI 风尚型","version":"2.0升 涡轮增压 190马力"},{"id":10,"vName":"2018款 30周年年型 Sportback 40 TFSI 运动型","version":"2.0升 涡轮增压 190马力"},{"id":11,"vName":"2018款 30周年年型 Limousine 40 TFSI 风尚型","version":"2.0升 涡轮增压 190马力"}]
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
         * id : 1
         * vName : 2018款 30周年年型 Sportback 35 TFSI 进取型
         * version : 1.4升 涡轮增压 150马力
         */

        private int id;
        private String vName;
        private String version;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getVName() {
            return vName;
        }

        public void setVName(String vName) {
            this.vName = vName;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }
    }
}
