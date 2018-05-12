package com.bao.island.util;

import android.graphics.Bitmap;

import java.io.File;

/**
 * Created by BAO on 2017/5/7.
 */
public interface ImageDownLoadCallBack {
    void onDownLoadSuccess(File file);
    void onDownLoadSuccess(Bitmap bitmap);

    void onDownLoadFailed();
}
