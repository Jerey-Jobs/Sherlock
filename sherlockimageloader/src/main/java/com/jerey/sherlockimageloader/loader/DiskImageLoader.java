package com.jerey.sherlockimageloader.loader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.jerey.sherlockimageloader.BitmapRequest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @author xiamin
 * @date 7/8/17.
 */
public class DiskImageLoader extends BaseLoader {
    @Override
    protected Bitmap onLoad(BitmapRequest request) {

        try {
            FileInputStream inputStream = new FileInputStream(new File(request.getImageURL()))
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
}
