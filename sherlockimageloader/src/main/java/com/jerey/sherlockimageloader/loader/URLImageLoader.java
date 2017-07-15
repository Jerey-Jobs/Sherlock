package com.jerey.sherlockimageloader.loader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.jerey.sherlockimageloader.BitmapRequest;
import com.jerey.sherlockimageloader.utils.BitmapDecoder;
import com.jerey.sherlockimageloader.utils.ImageViewHelper;
import com.jerey.sherlockimageloader.utils.L;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 加载网络图片
 * @date 7/8/17.
 */
public class URLImageLoader extends BaseLoader {
    @Override
    protected Bitmap onLoad(BitmapRequest request) {

        URL imgUrl = null;
        HttpURLConnection connection = null;
        InputStream inputStream = null;
        try {
            imgUrl = new URL(request.getImageURL());
            connection = (HttpURLConnection) imgUrl.openConnection();
            connection.connect();

            int code = connection.getResponseCode();
            L.i("run response code : " + code);

            inputStream = new BufferedInputStream(connection.getInputStream());
            inputStream.mark(inputStream.available());
            /**
             * 二次读流，进行图片大小自适应
             */
            final InputStream finalInputStream = inputStream;
            BitmapDecoder bitmapDecoder = new BitmapDecoder() {
                @Override
                public Bitmap decodeBitmapWithOption(BitmapFactory.Options options) {
                    Bitmap bitmap = BitmapFactory.decodeStream(finalInputStream, null, options);
                    /**
                     * 第一次读取，读取完要恢复
                     */
                    if (options.inJustDecodeBounds) {
                        try {
                            finalInputStream.reset();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        try {
                            finalInputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    return bitmap;
                }
            };

            return bitmapDecoder.decodeBitmap(ImageViewHelper.getImageViewWidth(request.getImageView())
            ,ImageViewHelper.getImageViewHeight(request.getImageView()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
