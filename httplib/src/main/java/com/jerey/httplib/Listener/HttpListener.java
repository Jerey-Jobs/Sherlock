package com.jerey.httplib.Listener;

import com.jerey.httplib.Callback.HttpCallback;

import org.apache.http.HttpEntity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by xiamin on 7/4/17.
 */

public class HttpListener implements IHttpListener {


    HttpCallback mHttpCallback;

    public HttpListener(HttpCallback mHttpCallback) {
        this.mHttpCallback = mHttpCallback;
    }


    @Override
    public void onSuccess(HttpEntity httpEntity) {
        InputStream inputStream;

        try {
            inputStream = httpEntity.getContent();
            String string = getContent(inputStream);
            mHttpCallback.onResponse(string);
        } catch (IOException e) {
            e.printStackTrace();
            onFail(e);
        }
    }

    @Override
    public void onFail(Exception e) {
        mHttpCallback.onFail(e);
    }


    private String getContent(InputStream inputStream) {
        String content = null;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder sb = new StringBuilder();
            String line = null;
            try {
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
            } catch (IOException e) {
                mHttpCallback.onFail(e);
                System.out.println("Error=" + e.toString());
            } finally {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    System.out.println("Error=" + e.toString());

                }
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            mHttpCallback.onFail(e);
        }
        return content;
    }
}
