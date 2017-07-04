package com.jerey.httplib.Callback;

import com.google.gson.Gson;

/**
 * Created by xiamin on 7/4/17.
 */

public abstract class JsonCallback<T> extends HttpCallback {


    Class<T> mClass;

    public JsonCallback(Class<T> mClass) {
        this.mClass = mClass;
    }

    @Override
    protected T parseResponse(String content) {
        Gson gson = new Gson();
        return gson.fromJson(content, mClass);
    }
}
