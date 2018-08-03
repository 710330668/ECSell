package com.example.com.careasysell.options.model.response;

import java.util.List;

/**
 * Created by 71033 on 2018/8/3.
 */
public class OptionTypeResponse {

    /**
     * code : 200
     * data : [{"carTypeId":"1","typeName":"全国"},{"carTypeId":"2","typeName":"国产-现车"},{"carTypeId":"3","typeName":"中规-现车"},{"carTypeId":"4","typeName":"中规-期货"},{"carTypeId":"5","typeName":"美版-现车"},{"carTypeId":"6","typeName":"美版-期货"},{"carTypeId":"10","typeName":"欧版-期货"},{"carTypeId":"11","typeName":"墨西哥版-现车"},{"carTypeId":"12","typeName":"墨西哥版-期货"},{"carTypeId":"7","typeName":"中东-现车"},{"carTypeId":"8","typeName":"欧版"},{"carTypeId":"9","typeName":"加版"}]
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
         * carTypeId : 1
         * typeName : 全国
         */

        private String carTypeId;
        private String typeName;

        public String getCarTypeId() {
            return carTypeId;
        }

        public void setCarTypeId(String carTypeId) {
            this.carTypeId = carTypeId;
        }

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }
    }
}
