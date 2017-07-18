package com.jerey.sherlockimageloader.loader.loaderPolicy;

import com.jerey.sherlockimageloader.BitmapRequest;

/**
 * 加载策略
 * @author xiamin
 * @date 7/8/17.
 */
public interface ILoadPolicy {

    /**
     * 两个加载请求进行优先级比较
     * @param request1
     * @param request2
     * @return
     */
    int compareTo(BitmapRequest request1, BitmapRequest request2);
}
