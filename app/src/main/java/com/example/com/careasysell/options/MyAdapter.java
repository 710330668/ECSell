package com.example.com.careasysell.options;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.com.careasysell.R;
import com.example.com.careasysell.options.model.CarBrandModel;

import java.util.List;

public class MyAdapter extends BaseAdapter {
    private List<CarBrandModel> list;
    private LayoutInflater inflater;
    private SelectBrandListener listener;

    public MyAdapter(Context context, List<CarBrandModel> list) {
        inflater = LayoutInflater.from(context);
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.list_item, null);
            holder.tv_word = (TextView) convertView.findViewById(R.id.tv_word);
            holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            holder.ivMore = convertView.findViewById(R.id.iv_more);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        String word = list.get(position).getHeaderWord();
        holder.tv_word.setText(word);
        holder.tv_name.setText(list.get(position).getName());
        //将相同字母开头的合并在一起
        if (position == 0) {
            //第一个是一定显示的
            holder.tv_word.setVisibility(View.VISIBLE);
        } else {
            //后一个与前一个对比,判断首字母是否相同，相同则隐藏
            String headerWord = list.get(position - 1).getHeaderWord();
            if (word.equals(headerWord)) {
                holder.tv_word.setVisibility(View.GONE);
            } else {
                holder.tv_word.setVisibility(View.VISIBLE);
            }
        }
        holder.ivMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String carBrand = list.get(position).getName();
                listener.selectBrand(carBrand);
            }
        });
        return convertView;
    }


    private class ViewHolder {
        private TextView tv_word;
        private TextView tv_name;
        private ImageView ivMore;
    }

    interface SelectBrandListener{
        void selectBrand(String carBrand);
    }

    public void setOnSelectBrandListener(SelectBrandListener listener){
        this.listener = listener;
    }


}
