package com.jerey.sherlockimageloader;

import com.jerey.sherlockimageloader.loader.ILoader;
import com.jerey.sherlockimageloader.loader.LoaderManager;
import com.jerey.sherlockimageloader.utils.L;

import java.util.concurrent.BlockingQueue;

/**
 * 请求转发线程
 * 依赖于BitmapRequest
 * 依赖于Loader
 * Created by xiamin on 7/8/17.
 */
public class RequestDispacher extends Thread {

    private BlockingQueue<BitmapRequest> mBitmapRequests;

    /** Used for telling us to die. */
    private volatile boolean mQuit = false;


    public RequestDispacher(BlockingQueue<BitmapRequest> requests) {
        mBitmapRequests = requests;
    }


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
                L.d("开始处理" + bitmapRequest.getSerialNo() + "号请求,线程号:" + Thread.currentThread()
                        .getId());
                /**
                 * 处理请求对象
                 */
                String type = parseURL(bitmapRequest.getImageURL());
                ILoader l = LoaderManager.getInstance().getLoaderByType(type);
                l.loadImage(bitmapRequest);

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

    /**
     * 解析图片来源
     * @param imageURL
     * @return
     */
    private String parseURL(String imageURL) {
        if (imageURL.contains("://")) {
            return imageURL.split("://")[0];
        }

        L.e("不支持的URL：" + imageURL);
        return " ";

    }
}
