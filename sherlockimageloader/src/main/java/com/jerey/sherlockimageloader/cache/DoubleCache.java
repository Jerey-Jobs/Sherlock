package com.jerey.sherlockimageloader.cache;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import com.jerey.sherlockimageloader.BitmapRequest;

/**
 * @author Xiamin
 * @date 2017/7/15
 */
public class DoubleCache implements BitmapCache {
    public static final String TAG = "DoubleCache";
    private RAMCache mRAMCache;
    private ROMCache mROMCache;

    public DoubleCache(Context context) {
        mRAMCache = new RAMCache();
        mROMCache = new ROMCache(context);
    }

    @Override
    public void put(BitmapRequest bitmapRequest, Bitmap bitmap) {
        mRAMCache.put(bitmapRequest, bitmap);
        mROMCache.put(bitmapRequest, bitmap);
    }

    @Override
    public Bitmap get(BitmapRequest request) {
        Bitmap bitmap;

        bitmap = mRAMCache.get(request);
        if (bitmap != null) {
            Log.d(TAG, "从内存缓存获取bitmap成功");
            return bitmap;
        }

        bitmap = mROMCache.get(request);
        if (bitmap != null) {
            Log.d(TAG, "从磁盘缓存获取bitmap成功");
            return bitmap;
        }
        return null;
    }

    @Override
    public void remove(BitmapRequest request) {
        mRAMCache.remove(request);
        mROMCache.remove(request);
    }
}
