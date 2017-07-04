package com.jerey.httplib.Callback;

import android.os.Handler;
import android.os.Looper;

/**
 * 结果回调
 *
 * @author xiamin
 */

public abstract class HttpCallback<T> {

    public Handler handler = new Handler(Looper.getMainLooper());

    protected abstract T parseResponse(String content);

    public void onResponse(String content) {
        notify(parseResponse(content));
    }

    protected void notify(final T t) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                onSuccess(t);
            }
        });
    }

    public abstract void onSuccess(T t);

    public abstract void onFail(Exception e);
}
