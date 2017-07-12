package com.jerey.sherlockimageloader;


import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;

import com.jerey.sherlockimageloader.ImageLoaderConfig.DisplayConfig;
import com.jerey.sherlockimageloader.ImageLoaderConfig.ImageLoaderConfig;

/**
 * @author xiamin
 */
public class SherlockImageLoader {


    public ImageLoaderConfig getImageLoaderConfig() {
        return mImageLoaderConfig;
    }

    public RequestQueue getRequestQueue() {
        return mRequestQueue;
    }

    private ImageLoaderConfig mImageLoaderConfig;
    private RequestQueue mRequestQueue;

    /**
     * 单列对象
     */
    private static volatile SherlockImageLoader instance;

    private SherlockImageLoader() {
    }


    public SherlockImageLoader(ImageLoaderConfig imageLoaderConfig) {
        mImageLoaderConfig = imageLoaderConfig;
        mRequestQueue = new RequestQueue(mImageLoaderConfig.getTHREAD_COUNT());
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
        display(imageView, url, null);
    }


    public void display(String url, Callback callback) {
        display(null, url, callback);
    }

    /**
     * @param imageView
     * @param url
     * @param callback
     */
    public void display(ImageView imageView, String url, Callback callback, DisplayConfig
            displayConfig) {
        BitmapRequest bitmapRequest = new BitmapRequest(imageView, url, callback, displayConfig);
        mRequestQueue.addRequest(bitmapRequest);
    }


    /**
     * 供用户自定义使用
     */
    public static interface Callback {
        /**
         * @param bitmap
         * @param url
         */
        void onSuccess(Bitmap bitmap, String url);

    }

}
