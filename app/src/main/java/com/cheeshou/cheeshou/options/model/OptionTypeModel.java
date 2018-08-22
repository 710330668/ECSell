package com.cheeshou.cheeshou.options.model;

/**
 * Created by 71033 on 2018/7/26.
 */
public class OptionTypeModel {

    private String optionType ;

    private String optionId;

    public String getOptionId() {
        return optionId;
    }

    public void setOptionId(String optionId) {
        this.optionId = optionId;
    }

    public String getOptionType() {
        return optionType;
    }

    public void setOptionType(String optionType) {
        this.optionType = optionType;
    }

    public OptionTypeModel(String optionId,String optionType) {
        this.optionType = optionType;
        this.optionId = optionId;
    }
}
