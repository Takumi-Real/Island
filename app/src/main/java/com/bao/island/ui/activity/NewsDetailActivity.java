package com.bao.island.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bao.island.R;
import com.bao.island.app.BaseActivity;
import com.bao.island.model.Favor;
import com.bao.island.model.DataManager;
import com.bao.island.model.NewsDetail;
import com.bao.island.ui.baike.activity.PhotoActivity;
import com.squareup.picasso.Picasso;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class NewsDetailActivity extends BaseActivity {


    private static String TAG = NewsDetailActivity.class.getSimpleName();
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_desc)
    TextView tvDesc;
    @BindView(R.id.tv_detail)
    TextView tvDetail;
    @BindView(R.id.linear_img)
    LinearLayout linearImg;
    String title, url;
    NewsDetail detail;
    @BindView(R.id.fab_collect)
    FloatingActionButton fabCollect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_news_detail);
        handleIntent();

        setFabState(url);
        getToolbar().setTitle(title);
        new GetNewsDetailTask().execute(url);
    }

    private void handleIntent() {
        title = getIntent().getStringExtra("title");
        url = getIntent().getStringExtra("url");

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_news_detail;
    }

    @OnClick(R.id.fab_collect)
    public void onViewClicked() {

        if (fabCollect.isSelected()){
            fabCollect.setSelected(false);
            DataSupport.deleteAll(Favor.class,"url = ? ",url);
        }else {
            fabCollect.setSelected(true);
            Favor c = new Favor();
            c.setTitle(title);
            c.setUrl(url);
            c.setType(1);
            c.save();
            Log.i(TAG, "onViewClicked: Saving...");
        }

        Toast.makeText(this, "哈哈哈.....", Toast.LENGTH_SHORT).show();
    }

    @SuppressLint("StaticFieldLeak")
    private class GetNewsDetailTask extends AsyncTask<String, Integer, NewsDetail> {

        @Override
        protected NewsDetail doInBackground(String... strings) {
            NewsDetail detail = new NewsDetail();
            detail = DataManager.getNewsDetail(strings[0]);

            return detail;
        }


        @Override
        protected void onPostExecute(NewsDetail newsDetail) {
            super.onPostExecute(newsDetail);
            tvTitle.setText(newsDetail.getTitle());
            tvDesc.setText(newsDetail.getDesc());
            tvDetail.setText(newsDetail.getContent());
            System.out.println(newsDetail.getContent());
            final List<String> imgs = newsDetail.getImgs();
            if (imgs.size() > 0) {

                for (int i = 0; i < imgs.size(); i++) {
                    ImageView iv = new ImageView(getApplicationContext());
                    ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                            500);
                    iv.setLayoutParams(params);
                    iv.setBackgroundColor(Color.WHITE);
                    iv.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    final int positon = i;
                    iv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent();

                            intent.putStringArrayListExtra("imglist", (ArrayList<String>) imgs);
                            intent.putExtra("position", positon);
                            intent.setClass(NewsDetailActivity.this, PhotoActivity.class);
                            startActivity(intent);
                        }
                    });
                    Picasso.get().load(imgs.get(i)).into(iv);
                    linearImg.addView(iv);

                }
            }
        }
    }



    private void setFabState(String url){
        List<Favor> cc = DataSupport.where(new String[]{"url = ?",url}).find(Favor.class);
        if (cc.size() > 0){
            fabCollect.setSelected(true);
            Log.i(TAG, "setFabState: Data Exist !!!");
        }else {
            fabCollect.setSelected(false);
            Log.i(TAG, "setFabState: Data Not Exist...");
        }
    }
}
