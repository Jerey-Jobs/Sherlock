package com.jerey.sherlockimageloader.cache;

import android.graphics.Bitmap;

import com.jerey.sherlockimageloader.BitmapRequest;

/**
 * Created by xiamin on 7/8/17.
 */

public interface BitmapCache {

    /**
     * 缓存bitmap
     * @param bitmapRequest
     * @param bitmap
     */
    void put(BitmapRequest bitmapRequest, Bitmap bitmap);

    /**
     * 通过请求取bitmap
     * @return
     */
    Bitmap get(BitmapRequest request);

    /**
     * 移除bitmap
     * @param request
     */
    void remove(BitmapRequest request);
}
