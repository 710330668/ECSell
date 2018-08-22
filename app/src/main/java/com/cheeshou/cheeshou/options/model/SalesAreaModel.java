package com.cheeshou.cheeshou.options.model;

/**
 * Created by 71033 on 2018/7/27.
 */
public class SalesAreaModel {

    private String salesAreaName;

    private String salesAreaId;

    public String getSalesAreaId() {
        return salesAreaId;
    }

    public void setSalesAreaId(String salesAreaId) {
        this.salesAreaId = salesAreaId;
    }

    public String getSalesAreaName() {
        return salesAreaName;
    }

    public void setSalesAreaName(String salesAreaName) {
        this.salesAreaName = salesAreaName;
    }

    public SalesAreaModel(String salesAreaId,String salesAreaName) {
        this.salesAreaId = salesAreaId;
        this.salesAreaName = salesAreaName;
    }
}
