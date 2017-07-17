package com.jerey.sherlockimageloader.cache;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.jerey.sherlockimageloader.BitmapRequest;
import com.jerey.sherlockimageloader.utils.L;

/**
 * Created by xiamin on 7/8/17.
 */

public class RAMCache implements BitmapCache {

    private LruCache<String, Bitmap> mLruCache;

    public RAMCache() {
        int maxSize = (int) (Runtime.getRuntime().maxMemory() / 4);
        L.d("RAMCache maxSize: " + maxSize);
        mLruCache = new LruCache<String, Bitmap>(maxSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                int size = value.getRowBytes() * value.getHeight();
                L.d("size = " + size);
                return size;
            }
        };

    }

    @Override
    public void put(BitmapRequest bitmapRequest, Bitmap bitmap) {
        mLruCache.put(bitmapRequest.getImageUriMD5(), bitmap);
    }

    @Override
    public Bitmap get(BitmapRequest request) {
        return mLruCache.get(request.getImageUriMD5());
    }

    @Override
    public void remove(BitmapRequest request) {
        mLruCache.remove(request.getImageUriMD5());
    }
}
