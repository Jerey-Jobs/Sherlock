package com.jerey.sherlockimageloader.loader;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;

import com.jerey.sherlockimageloader.BitmapRequest;
import com.jerey.sherlockimageloader.SherlockImageLoader;
import com.jerey.sherlockimageloader.cache.BitmapCache;
import com.jerey.sherlockimageloader.utils.L;

/**
 *
 */
public abstract class BaseLoader implements ILoader {

    public static BitmapCache mBitmapCache = SherlockImageLoader.getInstance()
            .getImageLoaderConfig()
            .getmBitmapCache();
    public static Handler handler = new Handler(Looper.getMainLooper());

    @Override
    public void loadImage(BitmapRequest request) {
        /** 从缓存中取bitmap */
        Bitmap bitmap = mBitmapCache.get(request);

        if (bitmap == null) {
            L.i("获取缓存失败，先显示Loading图");
            showLoadingImage(request);

            bitmap = onLoad(request);

            cacheBitmap(request, bitmap);
        }

        deliveryToUIThread(request, bitmap);
    }

    /**
     * 缓存图片
     * @param request
     * @param bitmap
     */
    private void cacheBitmap(BitmapRequest request, Bitmap bitmap) {
        if (request != null && bitmap != null) {
            synchronized (BaseLoader.class) {
                mBitmapCache.put(request, bitmap);
            }
        }
    }

    protected abstract Bitmap onLoad(BitmapRequest request);

    /**
     * 显示加载中图片
     * @param request
     */
    private void showLoadingImage(final BitmapRequest request) {
        if (request.getDisplayConfig() != null) {
            final ImageView imageview = request.getImageView();
            if (imageview == null) {
                return;
            }
            handler.post(new Runnable() {
                @Override
                public void run() {
                    imageview.setImageResource(request.getDisplayConfig().loadingImage);
                }
            });
        }
    }


    /**
     * 交给主线程显示
     * @param request
     * @param bitmap
     */
    protected void deliveryToUIThread(final BitmapRequest request, final Bitmap bitmap) {
        ImageView imageView = request.getImageView();
        L.d("deliveryToUIThread imageView = " + imageView);
        if (imageView == null) {
            return;
        }
        handler.post(new Runnable() {
            @Override
            public void run() {
                updateImageView(request, bitmap);
            }
        });

    }

    private void updateImageView(final BitmapRequest request, final Bitmap bitmap) {
        ImageView imageView = request.getImageView();
        L.d("更新UI");
        if (imageView == null) {
            L.d("为空.返回");
            return;
        }

        //加载正常  防止图片错位
        if (bitmap != null && imageView.getTag().equals(request.getImageURL())) {
            L.d("加载正常");
            imageView.setImageBitmap(bitmap);
        } else {
            L.d("加载失败,TAG不对");
        }
        //有可能加载失败
        if (bitmap == null
                && request.getDisplayConfig() != null
                && request.getDisplayConfig().failedImage != -1) {
            L.d("加载失败,显示默认");
            imageView.setImageResource(request.getDisplayConfig().failedImage);
        }
        //监听
        //回调 给圆角图片  特殊图片进行扩展
        if (request.getCallback() != null) {
            request.getCallback().onSuccess(bitmap, request.getImageURL());
        }
    }
}
