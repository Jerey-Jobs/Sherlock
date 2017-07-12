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

    /** Used for telling us to die. */
    private volatile boolean mQuit = false;

    /**
     * Forces this dispatcher to quit immediately.  If any requests are still in
     * the queue, they are not guaranteed to be processed.
     */
    public void quit() {
        mQuit = true;
        interrupt();
    }

    @Override
    public void run() {
        /**不断获取请求，处理请求*/
        while (!isInterrupted()) {
            try {
                BitmapRequest bitmapRequest = mBitmapRequests.take();

                if (bitmapRequest.isCancel()) {
                    continue;
                }



            } catch (InterruptedException e) {
                // We may have been interrupted because it was time to quit.
                e.printStackTrace();
                if (mQuit) {
                    return;
                }
                continue;
            }
        }
    }
}
