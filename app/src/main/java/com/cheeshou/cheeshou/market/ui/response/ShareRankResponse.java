package com.cheeshou.cheeshou.market.ui.response;

import java.util.List;

public class ShareRankResponse {
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
        private String count;
        private List<listsBean> lists;

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public List<listsBean> getLists() {
            return lists;
        }

        public void setLists(List<listsBean> lists) {
            this.lists = lists;
        }

        //            browseNum:浏览数
//            shareNum：分享数
//            userId：用户id
//            userName：用户名
//            userPic：用户头像
    }

    public static class listsBean {
        private String browseNum;
        private String shareNum;
        private String userId;
        private String userName;
        private String userPic;

        public String getBrowseNum() {
            return browseNum;
        }

        public void setBrowseNum(String browseNum) {
            this.browseNum = browseNum;
        }

        public String getShareNum() {
            return shareNum;
        }

        public void setShareNum(String shareNum) {
            this.shareNum = shareNum;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserPic() {
            return userPic;
        }

        public void setUserPic(String userPic) {
            this.userPic = userPic;
        }
    }

}
