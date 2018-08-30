package com.cheeshou.cheeshou.dealer.ui.model.response;

public class ShareUrlResponse {


    /**
     * code : 200
     * data : {"shareUrl":"http://localhost:8080/share/visitShareInfo?shareId=8de72829-8db2-4334-9f89-3ef68ff9f7b4"}
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
         * shareUrl : http://localhost:8080/share/visitShareInfo?shareId=8de72829-8db2-4334-9f89-3ef68ff9f7b4
         */

        private String shareUrl;

        public String getShareUrl() {
            return shareUrl;
        }

        public void setShareUrl(String shareUrl) {
            this.shareUrl = shareUrl;
        }
    }
}
