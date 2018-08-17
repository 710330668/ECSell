package com.example.com.careasysell.dealer.ui.model.response;

import java.util.List;

/**
 * Created by 71033 on 2018/8/16.
 */
public class ToShopResponse {


    /**
     * code : 200
     * data : {"count":13,"lists":[{"audiName":"奥迪RS 4","brandName":"AC Schnitzer","carCount":1,"createDate":1533469561000,"customerId":"e943de9297e14b8c8fb7c626dc084b01","maxBudget":50,"minBudget":25,"name":"6666","needTxt":"24234","progressContent":"新建客户","progressDate":1534255163000,"sex":0,"status":"SUCCESS","statusName":"已成交","userName":"销售"},{"audiName":"奥迪RS 4","brandName":"AC Schnitzer","carCount":1,"createDate":1533469561000,"customerId":"e943de9297e14b8c8fb7c626dc084b01","maxBudget":50,"minBudget":25,"name":"6666","needTxt":"24234","progressContent":"名称：555改成6666电话：555改成33333微信：55改成8888性别：女改成女","progressDate":1534255191000,"sex":0,"status":"SUCCESS","statusName":"已成交","userName":"销售"},{"audiName":"奥迪RS 4","brandName":"AC Schnitzer","carCount":1,"createDate":1534344541000,"customerId":"2372c8caa9bc4ad885360bf007d721f2","maxBudget":12,"minBudget":2,"name":"测试到店客户","needTxt":"24234","progressContent":"新建客户","progressDate":1534344541000,"sex":1,"status":"YES_STORE","statusName":"已到店","userName":"经销商"},{"audiName":"奥迪RS 4","brandName":"AC Schnitzer","carCount":1,"createDate":1533563997000,"customerId":"cbae9522bfa84910bde7c441c5380522","maxBudget":50,"minBudget":20,"name":"555233","needTxt":"24234","progressContent":"新建客户","progressDate":1534255192000,"sex":1,"status":"YES_STORE","statusName":"已到店","userName":"销售"},{"audiName":"奥迪RS 4","brandName":"AC Schnitzer","carCount":1,"createDate":1533563997000,"customerId":"cbae9522bfa84910bde7c441c5380522","maxBudget":50,"minBudget":20,"name":"555233","needTxt":"24234","progressContent":"有购车意向","progressDate":1534255194000,"sex":1,"status":"YES_STORE","statusName":"已到店","userName":"销售"},{"audiName":"奥迪RS 4","brandName":"AC Schnitzer","carCount":1,"createDate":1533563997000,"customerId":"cbae9522bfa84910bde7c441c5380522","maxBudget":50,"minBudget":20,"name":"555233","needTxt":"24234","progressContent":"有购车意向-宝马","progressDate":1534255196000,"sex":1,"status":"YES_STORE","statusName":"已到店","userName":"销售"}]}
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
         * count : 13
         * lists : [{"audiName":"奥迪RS 4","brandName":"AC Schnitzer","carCount":1,"createDate":1533469561000,"customerId":"e943de9297e14b8c8fb7c626dc084b01","maxBudget":50,"minBudget":25,"name":"6666","needTxt":"24234","progressContent":"新建客户","progressDate":1534255163000,"sex":0,"status":"SUCCESS","statusName":"已成交","userName":"销售"},{"audiName":"奥迪RS 4","brandName":"AC Schnitzer","carCount":1,"createDate":1533469561000,"customerId":"e943de9297e14b8c8fb7c626dc084b01","maxBudget":50,"minBudget":25,"name":"6666","needTxt":"24234","progressContent":"名称：555改成6666电话：555改成33333微信：55改成8888性别：女改成女","progressDate":1534255191000,"sex":0,"status":"SUCCESS","statusName":"已成交","userName":"销售"},{"audiName":"奥迪RS 4","brandName":"AC Schnitzer","carCount":1,"createDate":1534344541000,"customerId":"2372c8caa9bc4ad885360bf007d721f2","maxBudget":12,"minBudget":2,"name":"测试到店客户","needTxt":"24234","progressContent":"新建客户","progressDate":1534344541000,"sex":1,"status":"YES_STORE","statusName":"已到店","userName":"经销商"},{"audiName":"奥迪RS 4","brandName":"AC Schnitzer","carCount":1,"createDate":1533563997000,"customerId":"cbae9522bfa84910bde7c441c5380522","maxBudget":50,"minBudget":20,"name":"555233","needTxt":"24234","progressContent":"新建客户","progressDate":1534255192000,"sex":1,"status":"YES_STORE","statusName":"已到店","userName":"销售"},{"audiName":"奥迪RS 4","brandName":"AC Schnitzer","carCount":1,"createDate":1533563997000,"customerId":"cbae9522bfa84910bde7c441c5380522","maxBudget":50,"minBudget":20,"name":"555233","needTxt":"24234","progressContent":"有购车意向","progressDate":1534255194000,"sex":1,"status":"YES_STORE","statusName":"已到店","userName":"销售"},{"audiName":"奥迪RS 4","brandName":"AC Schnitzer","carCount":1,"createDate":1533563997000,"customerId":"cbae9522bfa84910bde7c441c5380522","maxBudget":50,"minBudget":20,"name":"555233","needTxt":"24234","progressContent":"有购车意向-宝马","progressDate":1534255196000,"sex":1,"status":"YES_STORE","statusName":"已到店","userName":"销售"}]
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
             * audiName : 奥迪RS 4
             * brandName : AC Schnitzer
             * carCount : 1
             * createDate : 1533469561000
             * customerId : e943de9297e14b8c8fb7c626dc084b01
             * maxBudget : 50
             * minBudget : 25
             * name : 6666
             * needTxt : 24234
             * progressContent : 新建客户
             * progressDate : 1534255163000
             * sex : 0
             * status : SUCCESS
             * statusName : 已成交
             * userName : 销售
             */

            private String audiName;
            private String brandName;
            private int carCount;
            private long createDate;
            private String customerId;
            private int maxBudget;
            private int minBudget;
            private String name;
            private String needTxt;
            private String progressContent;
            private long progressDate;
            private int sex;
            private String status;
            private String statusName;
            private String userName;

            public String getAudiName() {
                return audiName;
            }

            public void setAudiName(String audiName) {
                this.audiName = audiName;
            }

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

            public String getNeedTxt() {
                return needTxt;
            }

            public void setNeedTxt(String needTxt) {
                this.needTxt = needTxt;
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

            public int getSex() {
                return sex;
            }

            public void setSex(int sex) {
                this.sex = sex;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
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
}
