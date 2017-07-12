package com.jerey.sherlockimageloader;

import com.jerey.sherlockimageloader.utils.L;

import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 请求队列,并调用Dispatcher处理请求
 * Created by xiamin on 7/8/17.
 */

public class RequestQueue {

    PriorityBlockingQueue<BitmapRequest> mRequestQueue = new PriorityBlockingQueue<>();

    private AtomicInteger mNo = new AtomicInteger(0);


    private int mThreadCount;

    private RequestDispacher[] mRequestDispachers;

    public RequestQueue(int threadCount) {
        mThreadCount = threadCount;

    }

    /**
     * start所有
     */
    public void start() {
        mRequestDispachers = new RequestDispacher[mThreadCount];
        for (int i = 0; i < mRequestDispachers.length; i++) {
            RequestDispacher dispacher = new RequestDispacher(mRequestQueue);
            mRequestDispachers[i] = dispacher;
            dispacher.start();
        }
    }

    /**
     * 停止所有请求,以interrupt异常的方式
     */
    public void stop() {
        for (int i = 0; i < mRequestDispachers.length; i++) {
            if (mRequestDispachers[i] != null) {
                mRequestDispachers[i].quit();
            }
        }
    }


    /**
     * 取消所有请求,但不停止分发器的运行
     */
    public void cancel() {
        for (BitmapRequest request : mRequestQueue) {
            request.setCancel(true);
        }
    }

    /**
     * 根据tag取消请求
     * @param tag
     */
    public void cancel(Object tag) {
        if (tag == null) {
            return;
        }

        for (BitmapRequest request : mRequestQueue) {
            if (tag.equals(request.getRequestTag())) {
                request.setCancel(true);
            }
        }
    }

    public void addRequest(BitmapRequest request) {
        if (!mRequestQueue.contains(request)) {
            /** 设置唯一标识 */
            request.setSerialNo(mNo.incrementAndGet());
            mRequestQueue.add(request);
            L.w("请求添加成功， 编号为：" + request.getSerialNo());
        } else {
            L.w("请求已经存在， 编号为：" + request.getSerialNo());
        }
    }
}
