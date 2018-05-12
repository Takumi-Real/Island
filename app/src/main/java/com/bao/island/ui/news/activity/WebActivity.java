package com.bao.island.ui.news.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.KeyEvent;

import com.bao.island.R;
import com.bao.island.app.BaseActivity;
import com.bao.island.widget.ProgressWebView;
import com.tencent.smtt.sdk.WebView;

import butterknife.BindView;

public class WebActivity extends BaseActivity {


    @BindView(R.id.webview)
    com.tencent.smtt.sdk.WebView webview;
    String title;
    String url;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handleIntent();
        getToolbar().setTitle(title);

        com.tencent.smtt.sdk.WebSettings settings = webview.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setAppCacheEnabled(true);
        settings.setDomStorageEnabled(true);
//        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setSupportZoom(false);

//        webview.setWebViewClient(new com.tencent.smtt.sdk.WebViewClient(){
//
//            @Override
//            public void onPageFinished(com.tencent.smtt.sdk.WebView webView, String s) {
//
//                String fun1 = "javascript: function getClass(parent,sClass)\n" +
//                        "{" +
//                        "var aEle=parent.getElementsByTagName('div');\n" +
//                        "var aResult=[];\n" +
//                        "var i=0;\n" +
//                        "for(i<0;i<aEle.length;i++)\n" +
//                        "{" +
//                        "if(aEle[i].className==sClass)\n" +
//                        "{" +
//                        "aResult.push(aEle[i]);\n" +
//                        "}" +
//                        "};\n" +
//                        "return aResult;\n" +
//                        "}";
//
//                webView.loadUrl(fun1);
//                String fun2 = "javascript: function hideOther() \n" +
//                        "{" +
//                        "getClass(document,'head')[0].style.display='none';\n" +
//                        "getClass(document,'tip')[0].style.display='none';\n" +
//                        "getClass(document,'deansubnav')[0].style.display='none';\n" +
//                        "getClass(document,'info_right')[0].style.display='none';\n" +
//                        "getClass(document,'lbt_timg')[0].style.display='none';\n" +
//                        "getClass(document,'llb')[0].style.display='none';\n" +
//                        "getClass(document,'deanfooter')[0].style.display='none';\n" +
//                        "getClass(document,'xiangguan MB10')[0].style.display='none';\n" +
//                        "getClass(document,'itj_lt it3')[0].style.display='none';\n" +
//                        "getClass(document,'fx_l')[0].style.display='none';\n" +
//                        "getClass(document,'fanpian')[0].style.display='none';\n" +
//                        "document.getElementById('m').style.display='none';\n" +
//                        "document.getElementById('linkNC').style.display='none';\n" +
//                        "}";
//                webView.loadUrl(fun2);
//                webView.loadUrl("javascript:hideOther();");
//                super.onPageFinished(webView, s);
//            }
//
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView webView, String s) {
//                webView.loadUrl(s);
//                return true;
//            }
//        });

        //在当晚webview打开链接
//        webview.setWebViewClient(new WebViewClient(){
//
//
//            @Override
//            public void onPageFinished(WebView view, String url) {
//
//                String fun1 = "javascript: function getClass(parent,sClass)\n" +
//                        "{" +
//                        "var aEle=parent.getElementsByTagName('div');\n" +
//                        "var aResult=[];\n" +
//                        "var i=0;\n" +
//                        "for(i<0;i<aEle.length;i++)\n" +
//                        "{" +
//                        "if(aEle[i].className==sClass)\n" +
//                        "{" +
//                        "aResult.push(aEle[i]);\n" +
//                        "}" +
//                        "};\n" +
//                        "return aResult;\n" +
//                        "}";
//
//                view.loadUrl(fun1);
//                String fun2 = "javascript: function hideOther() \n" +
//                        "{" +
//                        "getClass(document,'head')[0].style.display='none';\n" +
//                        "getClass(document,'tip')[0].style.display='none';\n" +
//                        "getClass(document,'deansubnav')[0].style.display='none';\n" +
//                        "getClass(document,'info_right')[0].style.display='none';\n" +
//                        "getClass(document,'lbt_timg')[0].style.display='none';\n" +
//                        "getClass(document,'llb')[0].style.display='none';\n" +
//                        "getClass(document,'deanfooter')[0].style.display='none';\n" +
//                        "getClass(document,'xiangguan MB10')[0].style.display='none';\n" +
//                        "getClass(document,'itj_lt it3')[0].style.display='none';\n" +
//                        "getClass(document,'fx_l')[0].style.display='none';\n" +
//                        "getClass(document,'fanpian')[0].style.display='none';\n" +
//                        "document.getElementById('m').style.display='none';\n" +
//                        "document.getElementById('linkNC').style.display='none';\n" +
//                        "}";
//                view.loadUrl(fun2);
//                view.loadUrl("javascript:hideOther();");
//
//                super.onPageFinished(view, url);
//            }
//
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
////                view.loadUrl(url);
//                return true;
//            }
//        });
//        System.out.println(url);
        webview.loadUrl(url);
    }

    private void handleIntent() {
        url = getIntent().getStringExtra("url");
        title = getIntent().getStringExtra("title");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_web;
    }
}
