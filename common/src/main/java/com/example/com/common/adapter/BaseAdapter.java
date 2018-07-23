package com.example.com.common.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

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
        holder.onBindViewHolder(dataList.get(position));
        if (listener != null && holder.enable()) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(v, ((ItemData)dataList.get(position)).data);
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
}