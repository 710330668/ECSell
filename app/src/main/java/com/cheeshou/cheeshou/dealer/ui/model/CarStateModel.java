package com.cheeshou.cheeshou.dealer.ui.model;

public class CarStateModel {

    private String stateName;
    private String stateCode;
    private boolean isSelected;

    public CarStateModel(String stateName, String stateCode, boolean isSelected) {
        this.stateName = stateName;
        this.stateCode = stateCode;
        this.isSelected = isSelected;
    }

    public CarStateModel(String stateName, String stateCode) {
        this.stateName = stateName;
        this.stateCode = stateCode;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
