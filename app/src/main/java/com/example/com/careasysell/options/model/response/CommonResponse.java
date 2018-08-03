package com.example.com.careasysell.options.model.response;

import java.util.List;

/**
 * Created by 71033 on 2018/8/3.
 */
public class CommonResponse {

    /**
     * code : 200
     * data : [{"dataName":"黑"},{"dataName":"红"}]
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
         * dataName : 黑
         */

        private String dataName;

        public String getDataName() {
            return dataName;
        }

        public void setDataName(String dataName) {
            this.dataName = dataName;
        }
    }

}
