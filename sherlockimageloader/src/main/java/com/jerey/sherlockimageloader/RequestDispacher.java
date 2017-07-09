package com.jerey.sherlockimageloader;

import java.util.concurrent.BlockingDeque;

/**
 * 请求转发线程
 * 依赖于BitmapRequest
 * 依赖于Loader
 * Created by xiamin on 7/8/17.
 */
public class RequestDispacher extends Thread {

    private BlockingDeque<BitmapRequest> mBitmapRequests;



    @Override
    public void run() {
        /**不断获取请求，处理请求*/
        while (!isInterrupted()) {
            try {
                BitmapRequest bitmapRequest = mBitmapRequests.take();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
