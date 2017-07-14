package com.jerey.sherlockimageloader.loader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.jerey.sherlockimageloader.BitmapRequest;
import com.jerey.sherlockimageloader.utils.L;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author xiamin
 * @date 7/8/17.
 */
public class URLImageLoader extends BaseLoader {
    @Override
    protected Bitmap onLoad(BitmapRequest request) {

        URL imgUrl = new URL(request.getImageURL());
        HttpURLConnection connection = imgUrl.openConnection();
        InputStream inputStream = null;
        try {
            connection.connect();

            int code = connection.getResponseCode();
            L.i("run response code : " + code);

            inputStream = new BufferedInputStream(connection.getInputStream());
            Bitmap bitmap = getBitmapInputStream(inputStream);

            return bitmap;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Bitmap getBitmapInputStream(InputStream is) {
        Bitmap bp;
        bp = BitmapFactory.decodeStream(is);

        return bp;
    }
}
