package com.jerey.sherlockimageloader.loader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import com.jerey.sherlockimageloader.BitmapRequest;
import com.jerey.sherlockimageloader.utils.BitmapDecoder;
import com.jerey.sherlockimageloader.utils.ImageViewHelper;

import java.io.File;

/**
 * @author xiamin
 * @date 7/8/17.
 */
public class DiskImageLoader extends BaseLoader {
    @Override
    protected Bitmap onLoad(BitmapRequest request) {
        /**
         * 得到本地图片路径
         */
        final String path = Uri.parse(request.getImageURL()).getPath();

        File file = new File(path);
        if (!file.exists()) {
            return null;
        }
        BitmapDecoder decoder = new BitmapDecoder() {
            @Override
            public Bitmap decodeBitmapWithOption(BitmapFactory.Options options) {
                return BitmapFactory.decodeFile(path, options);
            }
        };
        return decoder.decodeBitmap(ImageViewHelper.getImageViewWidth(request.getImageView())
                , ImageViewHelper.getImageViewHeight(request.getImageView()));
    }
}
