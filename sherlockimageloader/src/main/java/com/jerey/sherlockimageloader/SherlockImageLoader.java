package com.jerey.sherlockimageloader;


import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.jerey.sherlockimageloader.ImageLoaderConfig.DisplayConfig;
import com.jerey.sherlockimageloader.ImageLoaderConfig.ImageLoaderConfig;
import com.jerey.sherlockimageloader.builder.RequestBuilder;
import com.jerey.sherlockimageloader.cache.DoubleCache;
import com.jerey.sherlockimageloader.loader.loaderPolicy.ReverseLoaderPolicy;

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

    public SherlockImageLoader(Context context, ImageLoaderConfig imageLoaderConfig) {
        if (imageLoaderConfig != null) {
            mImageLoaderConfig = imageLoaderConfig;
        } else {
            mImageLoaderConfig = new ImageLoaderConfig.Builder()
                    .setBitmapCache(new DoubleCache(context))
                    .setLoadPolicy(new ReverseLoaderPolicy())
                    .build();
        }
        mRequestQueue = new RequestQueue(mImageLoaderConfig.getTHREAD_COUNT());
        mRequestQueue.start();
    }


    public static RequestBuilder with(Object tag) {
        return new RequestBuilder(tag);
    }


    /**
     * 初始化
     * @param context
     */
    public static void init(Context context) {
        init(context, null);
    }

    public static void init(Context context, ImageLoaderConfig config) {
        if (instance == null) {
            instance = new SherlockImageLoader(context, config);
        }
    }

    public static SherlockImageLoader getInstance() {
        if (instance == null) {
            throw new RuntimeException("SherlockImageLoader must init");
        }

        return instance;
    }

    public void display(ImageView imageView, String url) {
        display(imageView, url, null, null);
    }


    public void display(String url, Callback callback) {
        display(null, url, callback, null);
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


    public void display(BitmapRequest bitmapRequest) {
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
