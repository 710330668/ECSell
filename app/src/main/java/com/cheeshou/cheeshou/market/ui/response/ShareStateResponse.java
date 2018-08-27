package com.cheeshou.cheeshou.market.ui.response;

public class ShareStateResponse {
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
        private String myBrowseCount;
        private String myShareCount;
        private String myRank;

        public String getMyBrowseCount() {
            return myBrowseCount;
        }

        public void setMyBrowseCount(String myBrowseCount) {
            this.myBrowseCount = myBrowseCount;
        }

        public String getMyShareCount() {
            return myShareCount;
        }

        public void setMyShareCount(String myShareCount) {
            this.myShareCount = myShareCount;
        }

        public String getMyRank() {
            return myRank;
        }

        public void setMyRank(String myRank) {
            this.myRank = myRank;
        }
    }

}
