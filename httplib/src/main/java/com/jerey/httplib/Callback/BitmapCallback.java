package com.jerey.httplib.Callback;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayInputStream;

/**
 * Created by xiamin on 7/4/17.
 */

public abstract class BitmapCallback extends HttpCallback<Bitmap> {
    @Override
    protected Bitmap parseResponse(String content) {
        return BitmapFactory.decodeStream(new ByteArrayInputStream(content.getBytes()));
    }
}
