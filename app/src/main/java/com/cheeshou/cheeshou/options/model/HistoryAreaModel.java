package com.cheeshou.cheeshou.options.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by 71033 on 2018/8/29.
 */

@Entity
public class HistoryAreaModel {

    @Id(autoincrement = true)
    private Long id;


    private String provinceId;


    private String cityId;

    private String areaName;

    @Generated(hash = 1591509415)
    public HistoryAreaModel(Long id, String provinceId, String cityId,
            String areaName) {
        this.id = id;
        this.provinceId = provinceId;
        this.cityId = cityId;
        this.areaName = areaName;
    }

    @Generated(hash = 907818919)
    public HistoryAreaModel() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProvinceId() {
        return this.provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getCityId() {
        return this.cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getAreaName() {
        return this.areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }


    

}
