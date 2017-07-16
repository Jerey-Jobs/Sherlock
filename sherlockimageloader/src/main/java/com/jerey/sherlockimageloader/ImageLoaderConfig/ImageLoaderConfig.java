package com.jerey.sherlockimageloader.ImageLoaderConfig;

import com.jerey.sherlockimageloader.cache.BitmapCache;
import com.jerey.sherlockimageloader.cache.RAMCache;
import com.jerey.sherlockimageloader.loader.loaderPolicy.ILoadPolicy;
import com.jerey.sherlockimageloader.loader.loaderPolicy.ReverseLoaderPolicy;

/**
 * 管理ImageLoader的配置项
 * Created by xiamin on 7/8/17.
 */

public class ImageLoaderConfig {

    /** 图片缓存配置 */
    private BitmapCache mBitmapCache = new RAMCache();

    private ILoadPolicy loadPolicy = new ReverseLoaderPolicy();

    private DisplayConfig displayConfig = new DisplayConfig();
    /** 线程数,根据CPU */
    private int THREAD_COUNT = Runtime.getRuntime().availableProcessors();

    private ImageLoaderConfig() {
        displayConfig = new DisplayConfig();
    }

    public BitmapCache getmBitmapCache() {
        return mBitmapCache;
    }

    public ILoadPolicy getLoadPolicy() {
        return loadPolicy;
    }

    public DisplayConfig getDisplayConfig() {
        return displayConfig;
    }

    public int getTHREAD_COUNT() {
        return THREAD_COUNT;
    }

    public static class Builder {
        private ImageLoaderConfig config;

        public Builder() {
            config = new ImageLoaderConfig();
        }

        /**
         * 设置缓存
         * @param bitmapCache
         * @return
         */
        public Builder setBitmapCache(BitmapCache bitmapCache) {
            config.mBitmapCache = bitmapCache;
            return this;
        }

        /**
         * 设置加载策略
         * @param loadPolicy
         * @return
         */
        public Builder setLoadPolicy(ILoadPolicy loadPolicy) {
            config.loadPolicy = loadPolicy;
            return this;
        }

        /**
         * 设置线程数
         * @param num
         * @return
         */
        public Builder setThreadNum(int num) {
            config.THREAD_COUNT = num;
            return this;
        }

        /**
         * 设置默认加载中图片
         * @param id
         * @return
         */
        public Builder setLoadingImage(int id) {
            config.displayConfig.loadingImage = id;
            return this;
        }

        /**
         * 设置默认加载失败的图片
         * @param id
         * @return
         */
        public Builder setFailedImage(int id) {
            config.displayConfig.failedImage = id;
            return this;
        }

        public ImageLoaderConfig build() {
            return config;
        }

    }

}
