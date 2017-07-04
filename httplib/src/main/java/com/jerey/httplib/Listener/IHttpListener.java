package com.jerey.httplib.Listener;

import org.apache.http.HttpEntity;

/**
 * Created by xiamin on 7/4/17.
 */

public interface IHttpListener {
    void onSuccess(HttpEntity httpEntity);

    void onFail(Exception e);
}
