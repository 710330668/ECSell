package com.example.com.careasysell.dealer.ui.model;

/**
 * Created by 71033 on 2018/8/6.
 */
public class ClientModel {

    private String name;

    private String demand;

    private String follow;

    private String time;

    private String status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDemand() {
        return demand;
    }

    public void setDemand(String demand) {
        this.demand = demand;
    }

    public String getFollow() {
        return follow;
    }

    public void setFollow(String follow) {
        this.follow = follow;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ClientModel(String name, String demand, String follow, String time, String status) {
        this.name = name;
        this.demand = demand;
        this.follow = follow;
        this.time = time;
        this.status = status;
    }
}
