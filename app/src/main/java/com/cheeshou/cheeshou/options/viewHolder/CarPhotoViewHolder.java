package com.cheeshou.cheeshou.options.viewHolder;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.cheeshou.cheeshou.R;
import com.cheeshou.cheeshou.options.model.CarPhotoModel;
import com.example.com.common.adapter.BaseViewHolder;
import com.example.com.common.adapter.ItemData;
import com.example.com.imageloader.LoaderManager;

/**
 * Created by 71033 on 2018/8/4.
 */
public class CarPhotoViewHolder extends BaseViewHolder {

    private ImageView ivCarPhoto;
    private ImageView ivDelete;
    private OnImageDeleteListener listener;

    private static final String TAG = "CarPhotoViewHolder";

    /**
     * TODO
     * single view may be direct construction, eg: TextView view = new TextView(context);
     *
     * @param parent current no use, may be future use
     * @param view
     */
    public CarPhotoViewHolder(ViewGroup parent, View view) {
        super(parent, view);
    }

    @Override
    public void findViews() {
        ivCarPhoto = itemView.findViewById(R.id.iv_car_photo);
        ivDelete = itemView.findViewById(R.id.iv_delete);
    }

    @Override
    public void onBindViewHolder(Object data) {
        final ItemData itemData = (ItemData) data;
        CarPhotoModel model = (CarPhotoModel) itemData.data;
        if (model.getBitmap() != null) {
            ivCarPhoto.setImageBitmap(model.getBitmap());
        } else {
            Log.e(TAG, "onBindViewHolder: " + model.getImageUrl() );
            LoaderManager.getLoader().loadNet(ivCarPhoto, model.getImageUrl());
        }
        ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.removeImage(itemData.tag);
            }
        });

        if (model.getBitmap() == null) {
            ivDelete.setImageResource(R.mipmap.checked_true);
        }

    }


    public interface OnImageDeleteListener {
        void removeImage(int position);
    }


    public void setOnImageDeleteListener(OnImageDeleteListener listener) {
        this.listener = listener;
    }
}

