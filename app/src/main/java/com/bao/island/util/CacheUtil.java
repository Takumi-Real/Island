package com.bao.island.util;

import android.os.Environment;

import com.bao.island.app.APP;

import java.io.File;

/**
 * Created by BAO on 2018/5/12.
 */

public class CacheUtil {


    public static void clearCache(){
        deleteDir(APP.getContext().getCacheDir());
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            deleteDir(APP.getContext().getExternalCacheDir());
        }
    }



    private static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }
}
