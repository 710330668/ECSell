package com.cheeshou.cheeshou.options.model.response;

/**
 * Created by 71033 on 2018/8/23.
 */
public class RegisonNameResponse {


    /**
     * code : 200
     * data : {"cityName":"青岛市","id":370200,"pid":370000,"pname":"山东省"}
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
         * cityName : 青岛市
         * id : 370200
         * pid : 370000
         * pname : 山东省
         */

        private String cityName;
        private int id;
        private int pid;
        private String pname;

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

        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        public String getPname() {
            return pname;
        }

        public void setPname(String pname) {
            this.pname = pname;
        }
    }
}
