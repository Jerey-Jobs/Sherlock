package com.jerey.sherlockimageloader.loader.loaderPolicy;

import com.jerey.sherlockimageloader.BitmapRequest;

/**
 * @author xiamin
 * @date 7/8/17.
 */
public class ReverseLoaderPolicy implements ILoadPolicy{
    @Override
    public int compareTo(BitmapRequest request1, BitmapRequest request2) {
        return request2.getSerialNo() - request1.getSerialNo();
    }
}
