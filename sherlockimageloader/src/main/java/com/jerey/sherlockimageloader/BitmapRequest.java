package com.jerey.sherlockimageloader;

import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.jerey.sherlockimageloader.ImageLoaderConfig.DisplayConfig;
import com.jerey.sherlockimageloader.loader.loaderPolicy.ILoadPolicy;

import java.lang.ref.SoftReference;

/**
 * Bitmap请求封装
 * 实现Comparable接口，供优先级阻塞队列对其排序
 */
public class BitmapRequest implements Comparable<BitmapRequest> {

    /** 图片软应用，内存不足情况下不加载 */
    private SoftReference<ImageView> mImageViewSoftReference;
    /** 图片路径 */
    private String imageURL;
    /** 图片的md5码，做缓存唯一标识，因为图片名可能是非法字符，合法化一下 */
    private String imageUriMD5;
    /** 编号，请求的唯一标识 */
    private int serialNo;
    /** 下载完监听 */
    private SherlockImageLoader.Callback mCallback;
    /** 显示设置,要是为空,则使用全局的 */
    private DisplayConfig mDisplayConfig;
    /** 请求tag,供取消请求时使用 */
    private Object requestTag;
    /** 是否被取消 */
    private boolean isCancel = false;

    public BitmapRequest(ImageView imageView,
                         String imageURL,
                         SherlockImageLoader.Callback callback,
                         DisplayConfig displayConfig) {
        mImageViewSoftReference = new SoftReference<ImageView>(imageView);
        /** 设置可见的imageView的tag*/
        imageView.setTag(imageURL);
        this.imageURL = imageURL;
        this.mCallback = callback;
        this.mDisplayConfig = displayConfig;
    }

    public int getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(int serialNo) {
        this.serialNo = serialNo;
    }


    /**
     * 拿到全局加载策略，并且通过加载策略完成大小
     */
    private ILoadPolicy loadPolicy = SherlockImageLoader.getInstance().getImageLoaderConfig()
            .getLoadPolicy();

    @Override
    public int compareTo(@NonNull BitmapRequest o) {
        return loadPolicy.compareTo(o, this);
    }

    @Override
    public int hashCode() {
        int result = loadPolicy != null ? loadPolicy.hashCode() : 0;
        result = 31 * result + serialNo;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == null) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        BitmapRequest request = (BitmapRequest) obj;
        if (serialNo != request.serialNo) {
            return false;
        }
        return loadPolicy != null ? loadPolicy.equals(request.loadPolicy) : request.loadPolicy ==
                null;
    }

    public SoftReference<ImageView> getImageViewSoftReference() {
        return mImageViewSoftReference;
    }

    public void setImageViewSoftReference(SoftReference<ImageView> imageViewSoftReference) {
        mImageViewSoftReference = imageViewSoftReference;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getImageUriMD5() {
        return imageUriMD5;
    }

    public void setImageUriMD5(String imageUriMD5) {
        this.imageUriMD5 = imageUriMD5;
    }

    public SherlockImageLoader.Callback getCallback() {
        return mCallback;
    }

    public void setCallback(SherlockImageLoader.Callback callback) {
        mCallback = callback;
    }

    public DisplayConfig getDisplayConfig() {
        return mDisplayConfig;
    }

    public void setDisplayConfig(DisplayConfig displayConfig) {
        mDisplayConfig = displayConfig;
    }

    public ILoadPolicy getLoadPolicy() {
        return loadPolicy;
    }

    public void setLoadPolicy(ILoadPolicy loadPolicy) {
        this.loadPolicy = loadPolicy;
    }


    public Object getRequestTag() {
        return requestTag;
    }

    public void setRequestTag(Object requestTag) {
        this.requestTag = requestTag;
    }

    /**
     * 判断是否请求被取消,这个接口将被多次调用
     * @return
     */
    public boolean isCancel() {
        return isCancel;
    }

    public void setCancel(boolean cancel) {
        isCancel = cancel;
    }


}
