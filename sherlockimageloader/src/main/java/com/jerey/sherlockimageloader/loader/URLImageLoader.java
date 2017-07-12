package com.jerey.sherlockimageloader.loader;

import android.graphics.Bitmap;

import com.jerey.sherlockimageloader.BitmapRequest;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

/**
 * @author xiamin
 * @date 7/8/17.
 */
public class URLImageLoader extends BaseLoader {
    @Override
    protected Bitmap onLoad(BitmapRequest request) {

        HttpURLConnection connection = null;
        InputStream inputStream = null;
        try {
            inputStream = new BufferedInputStream(connection.getInputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
