package com.example.com.careasysell.options.model.response;

import java.util.List;

/**
 * Created by 71033 on 2018/8/5.
 */
public class AreaProvinceResponse {


    /**
     * code : 200
     * data : [{"cityName":"安徽省","id":340000},{"cityName":"澳门特别行政区","id":820000},{"cityName":"北京市","id":110000},{"cityName":"重庆市","id":500000},{"cityName":"福建省","id":350000},{"cityName":"甘肃省","id":620000},{"cityName":"广东省","id":440000},{"cityName":"广西壮族自治区","id":450000},{"cityName":"贵州省","id":520000},{"cityName":"海南省","id":460000},{"cityName":"河北省","id":130000},{"cityName":"黑龙江省","id":230000},{"cityName":"河南省","id":410000},{"cityName":"湖北省","id":420000},{"cityName":"湖南省","id":430000},{"cityName":"江苏省","id":320000},{"cityName":"江西省","id":360000},{"cityName":"吉林省","id":220000},{"cityName":"辽宁省","id":210000},{"cityName":"内蒙古自治区","id":150000},{"cityName":"宁夏回族自治区","id":640000},{"cityName":"青海省","id":630000},{"cityName":"山东省","id":370000},{"cityName":"上海市","id":310000},{"cityName":"山西省","id":140000},{"cityName":"陕西省","id":610000},{"cityName":"四川省","id":510000},{"cityName":"台湾省","id":710000},{"cityName":"天津市","id":120000},{"cityName":"香港特别行政区","id":810000},{"cityName":"新疆维吾尔自治区","id":650000},{"cityName":"西藏自治区","id":540000},{"cityName":"云南省","id":530000},{"cityName":"浙江省","id":330000}]
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
         * cityName : 安徽省
         * id : 340000
         */

        private String cityName;
        private int id;

        public String getCityName() {
            return cityName;
        }

        public void setCityName(String cityName) {
            this.cityName = cityName;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
