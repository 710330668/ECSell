package com.cheeshou.cheeshou.utils;

import com.cheeshou.cheeshou.options.contract.ICarSell;
import com.cheeshou.cheeshou.options.contract.ICarSell;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by 71033 on 2018/7/25.
 */
public class NotifyCallBackManager {

    private List<ICarSell.IPagerClose> commits = new ArrayList<>();
    public static NotifyCallBackManager instance;

    public NotifyCallBackManager(){

    }

    public static NotifyCallBackManager getInstance() {
        if (instance == null) {
            synchronized (NotifyCallBackManager.class) {
                if (instance == null) {
                    instance = new NotifyCallBackManager();
                }
            }
        }
        return instance;
    }

    public void registPagerCloseCallBack(ICarSell.IPagerClose callBack){

        if(!commits.contains(callBack)){
            commits.add(callBack);
        }

    }

    public void onCloseCallBack(){
        for(ICarSell.IPagerClose callback : commits){
            if(callback != null){
                callback.close();
            }
        }
    }

    public void removeCloseCallBack(ICarSell.IPagerClose callback){
        for(Iterator it = commits.iterator(); it.hasNext();){
            ICarSell.IPagerClose o = (ICarSell.IPagerClose) it.next();
            if(o == callback){
                it.remove();
            }
        }
    }

    public void removeAllCallBack(){
        for(ICarSell.IPagerClose callback : commits){
            commits.clear();
        }
    }

}
