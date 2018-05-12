package com.bao.island.util;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;

/**
 * Created by BAO on 2018/5/12.
 */

public class DownLoadImageService implements Runnable{

    private String url;
    private Context context;
    private String filename;
    private ImageDownLoadCallBack callBack;
    private File currentFile;


    public DownLoadImageService(String url, Context context, String filename, ImageDownLoadCallBack callBack) {
        this.url = url;
        this.context = context;
        this.filename = filename;
        this.callBack = callBack;
    }

    @Override
    public void run() {
        final File file = null;



        Picasso.get().load(url).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {

                if (bitmap != null) {
                    saveImageToGallery(context, bitmap, filename);
                    callBack.onDownLoadSuccess(bitmap);
                }else {
                    callBack.onDownLoadFailed();
                }
            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        });


    }


    private void saveImageToGallery(Context context, Bitmap bmp, String filename) {
        // 首先保存图片

        File appDir = new File(Environment.getExternalStorageDirectory(), "Islands");
        if (!appDir.exists()) {
            appDir.mkdir();
        }

        currentFile = new File(appDir, filename);

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(currentFile);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        // 最后通知图库更新
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                Uri.fromFile(new File(currentFile.getPath()))));
    }
}
