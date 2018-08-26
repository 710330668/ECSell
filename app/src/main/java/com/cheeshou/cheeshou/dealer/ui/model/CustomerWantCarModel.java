package com.cheeshou.cheeshou.dealer.ui.model;

public class CustomerWantCarModel {
    private String name;
    private CodeBean code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CodeBean getCode() {
        if (code == null) {
            return new CodeBean();
        }
        return code;
    }

    public void setCode(CodeBean code) {
        this.code = code;
    }

    public static class CodeBean {
        private String brandId;
        private String audiId;
        private String versionId;

        public String getBrandId() {
            return brandId;
        }

        public void setBrandId(String brandId) {
            this.brandId = brandId;
        }

        public String getAudiId() {
            return audiId;
        }

        public void setAudiId(String audiId) {
            this.audiId = audiId;
        }

        public String getVersionId() {
            return versionId;
        }

        public void setVersionId(String versionId) {
            this.versionId = versionId;
        }
    }
}
