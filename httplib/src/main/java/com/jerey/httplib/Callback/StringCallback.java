package com.jerey.httplib.Callback;

/**
 * Created by xiamin on 7/4/17.
 */

public abstract class StringCallback extends HttpCallback<String> {

    @Override
    protected String parseResponse(String content) {
        return content;
    }
}
