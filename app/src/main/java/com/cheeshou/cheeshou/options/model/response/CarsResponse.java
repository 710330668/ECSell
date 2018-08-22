package com.cheeshou.cheeshou.options.model.response;

import java.util.List;

/**
 * Created by 71033 on 2018/8/4.
 */
public class CarsResponse {


    /**
     * code : 200
     * data : [{"carSeries":"奥迪RS 4","id":10},{"carSeries":"奥迪RS 3","id":11},{"carSeries":"奥迪RS 6","id":12},{"carSeries":"奥迪RS 7","id":13},{"carSeries":"奥迪R8","id":14},{"carSeries":"奥迪TT RS","id":15},{"carSeries":"奥迪RS 5","id":16},{"carSeries":"奥迪RS Q3","id":17}]
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
         * carSeries : 奥迪RS 4
         * id : 10
         */

        private String carSeries;
        private int id;

        public String getCarSeries() {
            return carSeries;
        }

        public void setCarSeries(String carSeries) {
            this.carSeries = carSeries;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
