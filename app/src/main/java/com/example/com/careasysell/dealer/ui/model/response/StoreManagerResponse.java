package com.example.com.careasysell.dealer.ui.model.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 71033 on 2018/8/5.
 */
public class StoreManagerResponse implements Serializable {

    /**
     * code : 200
     * data : [{"carCount":0,"carStatus":"SHELVES","carStatusName":"已上架"},{"carCount":0,"carStatus":"RESERVE","carStatusName":"已预订"},{"carCount":0,"carStatus":"OUT_SALE","carStatusName":"已售"}]
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

    public static class DataBean implements Serializable {
        /**
         * carCount : 0
         * carStatus : SHELVES
         * carStatusName : 已上架
         */

        private int carCount;
        private String carStatus;
        private String carStatusName;

        public int getCarCount() {
            return carCount;
        }

        public void setCarCount(int carCount) {
            this.carCount = carCount;
        }

        public String getCarStatus() {
            return carStatus;
        }

        public void setCarStatus(String carStatus) {
            this.carStatus = carStatus;
        }

        public String getCarStatusName() {
            return carStatusName;
        }

        public void setCarStatusName(String carStatusName) {
            this.carStatusName = carStatusName;
        }
    }
}
