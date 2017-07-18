package com.jerey.sherlockimageloader.builder;

import android.widget.ImageView;

import com.jerey.sherlockimageloader.BitmapRequest;
import com.jerey.sherlockimageloader.ImageLoaderConfig.DisplayConfig;
import com.jerey.sherlockimageloader.SherlockImageLoader;

/**
 * @author xiamin
 * @date 7/18/17.
 */
public class RequestBuilder {

    private String url;
    /** 显示设置,要是为空,则使用全局的 */
    private DisplayConfig mDisplayConfig;
    /** 请求tag,供取消请求时使用 */
    private Object requestTag;


    public RequestBuilder(Object requestTag) {
        this.requestTag = requestTag;
    }

    public RequestBuilder withDisplayConfig(DisplayConfig displayConfig) {
        mDisplayConfig = displayConfig;
        return this;
    }

    public RequestBuilder loadingImage(int loadingImage) {
        if (mDisplayConfig == null) {
            mDisplayConfig = new DisplayConfig();
        }
        mDisplayConfig.loadingImage = loadingImage;
        return this;
    }

    public RequestBuilder errorImage(int errorImage) {
        if (mDisplayConfig == null) {
            mDisplayConfig = new DisplayConfig();
        }
        mDisplayConfig.failedImage = errorImage;
        return this;
    }


    public RequestBuilder setUrl(String url) {
        this.url = url;
        return this;
    }

    public void into(ImageView imageView) {
        if (imageView == null) {
            throw new IllegalArgumentException("imageview can not be null");
        }

        BitmapRequest bitmapRequest = new BitmapRequest(imageView, url, null, mDisplayConfig);
        bitmapRequest.setRequestTag(requestTag);
        SherlockImageLoader.getInstance().display(bitmapRequest);
    }

    public void into(SherlockImageLoader.Callback callback) {
        if (callback == null) {
            throw new IllegalArgumentException("callback can not be null");
        }

        BitmapRequest bitmapRequest = new BitmapRequest(null, url, callback, mDisplayConfig);
        bitmapRequest.setRequestTag(requestTag);
        SherlockImageLoader.getInstance().display(bitmapRequest);
    }
}
