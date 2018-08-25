package com.cheeshou.cheeshou.dealer.ui.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Author ： DasonYu
 * Date ： 2018/8/1
 * Email Address : 15764240573@163.com
 */

@Entity
public class SearchHistoryModel {

    //
    @Id(autoincrement = true)
    private Long historyId;

    //区分 位置 在哪一个页面进行的搜索
    private String searchPosition;
    //搜索历史内容
    private String searchHistory;
    //
    private Long timeShamp;

    @Generated(hash = 961335598)
    public SearchHistoryModel(Long historyId, String searchPosition,
            String searchHistory, Long timeShamp) {
        this.historyId = historyId;
        this.searchPosition = searchPosition;
        this.searchHistory = searchHistory;
        this.timeShamp = timeShamp;
    }

    @Generated(hash = 2050687136)
    public SearchHistoryModel() {
    }

    public String getSearchHistory() {
        return searchHistory;
    }

    public void setSearchHistory(String searchHistory) {
        this.searchHistory = searchHistory;
    }

    public Long getHistoryId() {
        return this.historyId;
    }

    public void setHistoryId(Long historyId) {
        this.historyId = historyId;
    }

    public String getSearchPosition() {
        return this.searchPosition;
    }

    public void setSearchPosition(String searchPosition) {
        this.searchPosition = searchPosition;
    }

    public Long getTimeShamp() {
        return this.timeShamp;
    }

    public void setTimeShamp(Long timeShamp) {
        this.timeShamp = timeShamp;
    }
}
