package com.example.com.imageloader;

import android.content.Context;
import android.widget.ImageView;

import java.io.File;

/**
 * 图片加载接口
 * Created by 710330 on 2018/4/13.
 */
public interface ILoader {


    void init(Context context);

    void loadNet(ImageView target, String url);

    void loadNet(ImageView target, String url, Options options);

    void loadResource(ImageView target, int resId, Options options);

    void loadAssets(ImageView target, String assetName, Options options);

    void loadFile(ImageView target, File file, Options options);

    void clearMemoryCache(Context context);

    void clearDiskCache(Context context);

    class Options {

        public static final int RES_NONE = -1;
        public int loadingResId = RES_NONE;//加载中的资源id
        public int loadErrorResId = RES_NONE;//加载失败的资源id

        public Options(int loadingResId, int loadErrorResId) {
            this.loadingResId = loadingResId;
            this.loadErrorResId = loadErrorResId;
        }
    }
}
