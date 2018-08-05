package com.example.com.careasysell.options.model;

/**
 * Created by 71033 on 2018/7/26.
 */
public class AreasModel {

    private String areasName ;

    private String areasId;

    public String getAreasName() {
        return areasName;
    }

    public void setAreasName(String areasName) {
        this.areasName = areasName;
    }

    public AreasModel(String areasName,String id) {
        this.areasName = areasName;
        this.areasId = id;
    }

    public String getAreasId() {
        return areasId;
    }

    public void setAreasId(String areasId) {
        this.areasId = areasId;
    }
}
