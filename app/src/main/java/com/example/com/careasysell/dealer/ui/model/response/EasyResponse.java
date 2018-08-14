package com.example.com.careasysell.dealer.ui.model.response;

/**
 * Created by 71033 on 2018/8/14.
 */
public class EasyResponse {


    /**
     * code : 200
     * data : {}
     * msg : 请求成功！
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
    }
}
