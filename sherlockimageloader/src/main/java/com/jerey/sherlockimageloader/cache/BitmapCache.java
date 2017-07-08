package com.jerey.sherlockimageloader.cache;

import android.graphics.Bitmap;

/**
 * Created by xiamin on 7/8/17.
 */

public interface BitmapCache {
    void put();

    Bitmap get();

    void remove();
}
