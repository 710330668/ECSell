package com.example.com.careasysell.options.model.response;

import java.util.List;

/**
 * Created by 71033 on 2018/8/3.
 */
public class CarBrandResponse {


    /**
     * code : 200
     * data : []
     * msg : 操作成功
     */

    private int code;
    private String msg;
    private List<?> data;

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

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}
