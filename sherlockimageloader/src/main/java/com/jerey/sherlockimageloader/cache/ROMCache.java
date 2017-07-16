package com.jerey.sherlockimageloader.cache;

import android.content.Context;
import android.graphics.Bitmap;

import com.jerey.sherlockimageloader.BitmapRequest;
import com.jerey.sherlockimageloader.utils.DiskLruCache.DiskLruCacheManager;

import java.io.IOException;

/**
 * Created by xiamin on 7/8/17.
 */

public class ROMCache implements BitmapCache {

    private final String IMAGE_DISK_NAME = "image";
    private DiskLruCacheManager mDiskLruCacheManager;

    /**
     * 初始化DiskLruCache，初始化100兆大小
     * @param context
     */
    public ROMCache(Context context) {
        try {
            mDiskLruCacheManager = new DiskLruCacheManager(context, IMAGE_DISK_NAME, 100 * 1024 *
                    1024);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void put(BitmapRequest bitmapRequest, Bitmap bitmap) {
        mDiskLruCacheManager.put(bitmapRequest.getImageUriMD5(), bitmap);
    }

    @Override
    public Bitmap get(BitmapRequest request) {
        return mDiskLruCacheManager.getAsBitmap(request.getImageUriMD5());
    }

    @Override
    public void remove(BitmapRequest request) {
        try {
            mDiskLruCacheManager.getDiskLruCache().remove(request.getImageUriMD5());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
