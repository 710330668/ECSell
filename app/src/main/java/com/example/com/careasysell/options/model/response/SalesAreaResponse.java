package com.example.com.careasysell.options.model.response;

import java.util.List;

/**
 * Created by 71033 on 2018/8/3.
 */
public class SalesAreaResponse {


    /**
     * code : 200
     * data : [{"areaId":"c0b124120c6f4e25a768119d813d145q","areaName":"北京"},{"areaId":"c0b124120c6f4e25a768119d8e3d145d","areaName":"华北"},{"areaId":"c0b124120c6f4e25a768119d8e3d145q","areaName":"999"}]
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
         * areaId : c0b124120c6f4e25a768119d813d145q
         * areaName : 北京
         */

        private String areaId;
        private String areaName;

        public String getAreaId() {
            return areaId;
        }

        public void setAreaId(String areaId) {
            this.areaId = areaId;
        }

        public String getAreaName() {
            return areaName;
        }

        public void setAreaName(String areaName) {
            this.areaName = areaName;
        }
    }
}
