package com.jerey.sherlockimageloader;

import com.jerey.sherlockimageloader.utils.L;

import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 请求队列,并调用Dispatcher处理请求
 * Created by xiamin on 7/8/17.
 */

public class RequestQueue {

    /** 生产者消费者模式完美解决 */
    PriorityBlockingQueue<BitmapRequest> mRequestQueue = new PriorityBlockingQueue<>();

    private AtomicInteger mNo = new AtomicInteger(0);

    private RequestDispacher[] mRequestDispachers;

    public RequestQueue() {

    }

    /**
     * start所有
     */
    public void start() {

    }

    /**
     * 停止所有请求
     */
    public void stop() {

    }

    /**
     * 根据tag停止请求
     * @param tag
     */
    public void stop(String tag) {

    }

    public void addRequest(BitmapRequest request) {
        if (!mRequestQueue.contains(request)) {
            request.setSerialNo(mNo.incrementAndGet());
            mRequestQueue.add(request);
        } else {
            L.w("请求已经存在， 编号为：" + request.getSerialNo());
        }
    }
}
