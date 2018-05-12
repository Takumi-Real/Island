package com.bao.island.app;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.tencent.smtt.sdk.QbSdk;

import org.litepal.LitePal;

/**
 * Created by BAO on 2018/5/9.
 */

public class APP extends Application {

    private static APP context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;


        LitePal.initialize(this);
        initX5WebView();
    }


    private void initX5WebView() {
        //搜集本地tbs内核信息并上报服务器，服务器返回结果决定使用哪个内核。
        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {
            @Override
            public void onViewInitFinished(boolean arg0) {
                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
                Log.d("app", " onViewInitFinished is " + arg0);
            }

            @Override
            public void onCoreInitFinished() {
            }
        };
        //x5内核初始化接口
        QbSdk.initX5Environment(getApplicationContext(), cb);
    }



    public static Context getContext(){
        return context;
    }
}
