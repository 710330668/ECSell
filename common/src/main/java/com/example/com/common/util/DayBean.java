package com.example.com.common.util;

/**
 * Created by 71033 on 2018/8/18.
 */
public class DayBean {
    private String time; //日期
    private String num; //星期几
    private boolean isToday;

    public DayBean(String time, String num, boolean isToday) {
        this.time = time;
        this.num = num;
        this.isToday = isToday;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public boolean isToday() {
        return isToday;
    }

    public void setToday(boolean today) {
        isToday = today;
    }
}
