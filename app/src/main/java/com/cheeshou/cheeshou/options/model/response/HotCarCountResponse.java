package com.cheeshou.cheeshou.options.model.response;

/**
 * Created by 71033 on 2018/8/18.
 */
public class HotCarCountResponse {


    /**
     * code : 200
     * data : {"browseCount":228,"issueCount":3,"shareCount":0,"shelvesCount":1}
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
         * browseCount : 228
         * issueCount : 3
         * shareCount : 0
         * shelvesCount : 1
         */

        private int browseCount;
        private int issueCount;
        private int shareCount;
        private int shelvesCount;

        public int getBrowseCount() {
            return browseCount;
        }

        public void setBrowseCount(int browseCount) {
            this.browseCount = browseCount;
        }

        public int getIssueCount() {
            return issueCount;
        }

        public void setIssueCount(int issueCount) {
            this.issueCount = issueCount;
        }

        public int getShareCount() {
            return shareCount;
        }

        public void setShareCount(int shareCount) {
            this.shareCount = shareCount;
        }

        public int getShelvesCount() {
            return shelvesCount;
        }

        public void setShelvesCount(int shelvesCount) {
            this.shelvesCount = shelvesCount;
        }
    }
}
