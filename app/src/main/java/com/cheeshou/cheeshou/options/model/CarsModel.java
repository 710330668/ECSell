package com.cheeshou.cheeshou.options.model;

/**
 * Created by 71033 on 2018/7/26.
 */
public class CarsModel {

    private String carsName ;

    private String carsId;

    public String getCarsName() {
        return carsName;
    }

    public void setCarsName(String carsName) {
        this.carsName = carsName;
    }

    public CarsModel(String carsName,String carsId) {
        this.carsName = carsName;
        this.carsId =carsId;
    }

    public String getCarsId() {
        return carsId;
    }

    public void setCarsId(String carsId) {
        this.carsId = carsId;
    }
}
