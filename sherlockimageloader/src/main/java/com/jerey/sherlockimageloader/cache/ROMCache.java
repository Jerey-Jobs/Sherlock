package com.jerey.sherlockimageloader.cache;

import android.graphics.Bitmap;

import com.jerey.sherlockimageloader.BitmapRequest;

/**
 * Created by xiamin on 7/8/17.
 */

public class ROMCache implements BitmapCache {
    @Override
    public void put(BitmapRequest bitmapRequest, Bitmap bitmap) {

    }

    @Override
    public Bitmap get(BitmapRequest request) {
        return null;
    }

    @Override
    public void remove(BitmapRequest request) {

    }
}
