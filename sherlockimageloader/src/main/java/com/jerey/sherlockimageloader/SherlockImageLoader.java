package com.jerey.sherlockimageloader;


import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;

import com.jerey.sherlockimageloader.ImageLoaderConfig.ImageLoaderConfig;

/**
 * @author xiamin
 */
public class SherlockImageLoader {


    private ImageLoaderConfig mImageLoaderConfig;
    private RequestQueue mRequestQueue;

    /** 单列对象 */
    private static volatile SherlockImageLoader instance;

    private SherlockImageLoader() {
    }


    public SherlockImageLoader(ImageLoaderConfig imageLoaderConfig) {
        mImageLoaderConfig = imageLoaderConfig;
    }


    public static void with(Fragment fragment) {

    }

    public static void with(View view) {

    }

    public static void with(Context context) {

    }


    public static void init(ImageLoaderConfig config) {
        if (instance == null) {
            instance = new SherlockImageLoader(config);
        }
    }

    public static SherlockImageLoader getInstance() {
        if (instance == null) {
            throw new RuntimeException("SherlockImageLoader must init");
        }

        return instance;
    }

    public void display(ImageView imageView, String url) {

    }

    public void display(ImageView imageView, String url,) {

    }

    /**
     * 供用户自定义使用
     */
    public static interface Callback {
        /**
         * @param view
         * @param bitmap
         * @param url
         */
        void onSuccess(ImageView view, Bitmap bitmap, String url);

    }

}
