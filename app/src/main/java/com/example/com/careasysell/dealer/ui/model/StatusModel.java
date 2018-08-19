package com.example.com.careasysell.dealer.ui.model;

public class StatusModel {
    private String statusName;
    private String statusCode;

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public StatusModel(String statusName, String statusCode) {
        this.statusName = statusName;
        this.statusCode = statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }
}
