package com.example.com.careasysell.options.model;

/**
 * Created by 71033 on 2018/7/26.
 */
public class CarsSeriesModel {

    private String seriesName;

    private String id;

    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }

    public CarsSeriesModel(String seriesName,String id) {
        this.seriesName = seriesName;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
