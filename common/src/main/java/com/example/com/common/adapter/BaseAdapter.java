package com.example.com.common.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.com.common.util.RefreshFooterViewHolder;

import java.util.Collections;
import java.util.List;

/**
 * Created by 71033 on 2017/10/9.
 */

public class BaseAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {

    /**
     * data
     */
    public List<T> dataList;

    /**
     * onClick
     */
    public onItemClickListener listener;


    public BaseDelegate delegate;

    // 当前加载状态，默认为加载完成
    public int loadState = 3;

    // 正在加载
    public final int LOADING = 1;
    // 加载完成
    public final int LOADING_COMPLETE = 2;
    // 加载到底
    public final int LOADING_END = 3;


    public BaseAdapter(List<T> dataList, BaseDelegate delegate) {
        this(dataList, delegate, null);
    }

    /**
     * constructor
     *
     * @param dataList
     * @param delegate
     * @param listener
     */
    public BaseAdapter(List<T> dataList, BaseDelegate delegate, onItemClickListener listener) {
        checkData(dataList);
        this.delegate = delegate;
        this.listener = listener;
    }


    private void checkData(List<T> dataList) {
        if (dataList == null) {
            dataList = Collections.emptyList();
        }
        this.dataList = dataList;
    }

    /**
     * create view holder
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return delegate.onCreateViewHolder(parent, viewType);
    }

    /**
     * bind view holder
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(BaseViewHolder holder, final int position) {
        if(holder instanceof RefreshFooterViewHolder){
            RefreshFooterViewHolder footViewHolder = (RefreshFooterViewHolder) holder;
            if(loadState == LOADING){// 正在加载
                footViewHolder.changeStatus(View.VISIBLE,View.VISIBLE,View.GONE);
            }else if(loadState == LOADING_COMPLETE){// 加载完成
                footViewHolder.changeStatus(View.GONE,View.GONE,View.GONE);
            }else{// 加载到底
                footViewHolder.changeStatus(View.GONE,View.GONE,View.VISIBLE);
            }
        }else {
            holder.onBindViewHolder(dataList.get(position));
            if (listener != null && holder.enable()) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.onClick(v, ((ItemData) dataList.get(position)).data);
                    }
                });
                holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        return listener.onLongClick(v, dataList.get(position));
                    }
                });
            }
        }
    }

    /**
     * @return
     */
    @Override
    public int getItemCount() {
        return dataList.size();
    }

    /**
     * get item view type
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        return delegate.getItemViewType(dataList.get(position));
    }


    /**
     * 设置上拉加载状态
     *
     * @param loadState 0.正在加载 1.加载完成 2.加载到底
     */
    public void setLoadState(int loadState) {
        this.loadState = loadState;
        notifyDataSetChanged();
    }
}