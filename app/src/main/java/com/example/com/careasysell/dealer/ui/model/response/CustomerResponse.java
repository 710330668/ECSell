package com.example.com.careasysell.dealer.ui.model.response;

import java.util.List;

public class CustomerResponse {

    /**
     * code : 200
     * data : {"count":14,"lists":[{"brandName":"AC Schnitzer","carCount":1,"createDate":1534344541000,"customerId":"2372c8caa9bc4ad885360bf007d721f2","maxBudget":12,"minBudget":2,"name":"测试到店客户","progressContent":"新建客户","progressDate":1534344541000,"statusName":"已到店","userName":"经销商"}]}
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
         * count : 14
         * lists : [{"brandName":"AC Schnitzer","carCount":1,"createDate":1534344541000,"customerId":"2372c8caa9bc4ad885360bf007d721f2","maxBudget":12,"minBudget":2,"name":"测试到店客户","progressContent":"新建客户","progressDate":1534344541000,"statusName":"已到店","userName":"经销商"}]
         */

        private int count;
        private List<ListsBean> lists;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<ListsBean> getLists() {
            return lists;
        }

        public void setLists(List<ListsBean> lists) {
            this.lists = lists;
        }

        public static class ListsBean {
            /**
             * brandName : AC Schnitzer
             * carCount : 1
             * createDate : 1534344541000
             * customerId : 2372c8caa9bc4ad885360bf007d721f2
             * maxBudget : 12
             * minBudget : 2
             * name : 测试到店客户
             * progressContent : 新建客户
             * progressDate : 1534344541000
             * statusName : 已到店
             * userName : 经销商
             */

            private String brandName;
            private int carCount;
            private long createDate;
            private String customerId;
            private int maxBudget;
            private int minBudget;
            private String name;
            private String progressContent;
            private long progressDate;
            private String statusName;
            private String userName;

            public String getBrandName() {
                return brandName;
            }

            public void setBrandName(String brandName) {
                this.brandName = brandName;
            }

            public int getCarCount() {
                return carCount;
            }

            public void setCarCount(int carCount) {
                this.carCount = carCount;
            }

            public long getCreateDate() {
                return createDate;
            }

            public void setCreateDate(long createDate) {
                this.createDate = createDate;
            }

            public String getCustomerId() {
                return customerId;
            }

            public void setCustomerId(String customerId) {
                this.customerId = customerId;
            }

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

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getProgressContent() {
                return progressContent;
            }

            public void setProgressContent(String progressContent) {
                this.progressContent = progressContent;
            }

            public long getProgressDate() {
                return progressDate;
            }

            public void setProgressDate(long progressDate) {
                this.progressDate = progressDate;
            }

            public String getStatusName() {
                return statusName;
            }

            public void setStatusName(String statusName) {
                this.statusName = statusName;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }
        }
    }

    @Override
    public String toString() {
        return "CustomerResponse{" +
                "code=" + code +
                ", data=" + data +
                ", msg='" + msg + '\'' +
                '}';
    }
}
