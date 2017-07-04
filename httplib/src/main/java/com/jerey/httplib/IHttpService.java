package com.jerey.httplib;

/**
 * Created by xiamin on 7/4/17.
 */

public interface IHttpService {

    /**
     * 设置请求路径
     */
    void setUrl(String url);

    /**
     * 设置请求参数
     *
     * @param key
     * @param data
     * @return
     */
    IHttpService addParam(String key, String data);

    /**
     * 执行
     */
    void execute();


    void setHttpListener();
}
