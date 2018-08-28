package com.cheeshou.cheeshou.dealer.ui.model.response;

import java.util.List;

public class FindCustomerNeedResponse {

    /**
     * code : 200
     * data : {"lists":[{"audiId":"10","audiName":"奥迪RS 4","brandId":"2","brandName":"AC Schnitzer","versionId":"1","versionName":"2018款 30周年年型 Sportback 35 TFSI 进取型"}],"maxBudget":2,"minBudget":1,"needTxt":"222","remark":"222222","reserveColumn1":"否"}
     * msg : 操作成功
     */

    private int code;
    private DataBean data;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        /**
         * lists : [{"audiId":"10","audiName":"奥迪RS 4","brandId":"2","brandName":"AC Schnitzer","versionId":"1","versionName":"2018款 30周年年型 Sportback 35 TFSI 进取型"}]
         * maxBudget : 2
         * minBudget : 1
         * needTxt : 222
         * remark : 222222
         * reserveColumn1 : 否
         */

        private int maxBudget;
        private int minBudget;
        private String needTxt;
        private String remark;
        private String reserveColumn1;
        private List<ListsBean> lists;

        public int getMaxBudget() {
            return maxBudget;
        }

        public void setMaxBudget(int maxBudget) {
            this.maxBudget = maxBudget;
        }

        public int getMinBudget() {
            return minBudget;
        }

        public void setMinBudget(int minBudget) {
            this.minBudget = minBudget;
        }

        public String getNeedTxt() {
            return needTxt;
        }

        public void setNeedTxt(String needTxt) {
            this.needTxt = needTxt;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getReserveColumn1() {
            return reserveColumn1;
        }

        public void setReserveColumn1(String reserveColumn1) {
            this.reserveColumn1 = reserveColumn1;
        }

        public List<ListsBean> getLists() {
            return lists;
        }

        public void setLists(List<ListsBean> lists) {
            this.lists = lists;
        }

        public static class ListsBean {
            /**
             * audiId : 10
             * audiName : 奥迪RS 4
             * brandId : 2
             * brandName : AC Schnitzer
             * versionId : 1
             * versionName : 2018款 30周年年型 Sportback 35 TFSI 进取型
             */

            private String audiId;
            private String audiName;
            private String brandId;
            private String brandName;
            private String versionId;
            private String versionName;

            public String getAudiId() {
                return audiId;
            }

            public void setAudiId(String audiId) {
                this.audiId = audiId;
            }

            public String getAudiName() {
                return audiName;
            }

            public void setAudiName(String audiName) {
                this.audiName = audiName;
            }

            public String getBrandId() {
                return brandId;
            }

            public void setBrandId(String brandId) {
                this.brandId = brandId;
            }

            public String getBrandName() {
                return brandName;
            }

            public void setBrandName(String brandName) {
                this.brandName = brandName;
            }

            public String getVersionId() {
                return versionId;
            }

            public void setVersionId(String versionId) {
                this.versionId = versionId;
            }

            public String getVersionName() {
                return versionName;
            }

            public void setVersionName(String versionName) {
                this.versionName = versionName;
            }
        }
    }
}
