package com.jerey.sherlockhttp;

import android.app.Application;

import com.jerey.sherlockimageloader.ImageLoaderConfig.ImageLoaderConfig;
import com.jerey.sherlockimageloader.SherlockImageLoader;
import com.jerey.sherlockimageloader.cache.DoubleCache;

/**
 * @author Xiamin
 * @date 2017/7/16
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SherlockImageLoader.init(this,
                new ImageLoaderConfig.Builder()
                        .setBitmapCache(new DoubleCache(this))
                        .setFailedImage(R.mipmap.ic_launcher)
                        .setLoadingImage(R.drawable.blog)
                        .setThreadNum(10)
                        .build());
    }


    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
