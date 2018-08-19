package com.example.com.careasysell.dealer.ui.model.response;

public class CustomerStatusResponse {
    private int code;
    private DataBean data;
    private String msg;

    private class DataBean {
        private String dataCode;
        private String dataName;

        public String getDataCode() {
            return dataCode;
        }

        public void setDataCode(String dataCode) {
            this.dataCode = dataCode;
        }

        public String getDataName() {
            return dataName;
        }

        public void setDataName(String dataName) {
            this.dataName = dataName;
        }
    }
}
