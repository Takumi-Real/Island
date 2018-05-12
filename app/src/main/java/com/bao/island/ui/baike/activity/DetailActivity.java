package com.bao.island.ui.baike.activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bao.island.R;
import com.bao.island.app.Constants;
import com.bao.island.model.Island;
import com.bao.island.util.DBManager;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.iv_cover)
    ImageView ivCover;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.fab_pics)
    FloatingActionButton fabPics;

    DBManager dbManager;

    SQLiteDatabase db;
    List<String> imglist ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        int id = getIntent().getIntExtra("id",100);
        String title = getIntent().getStringExtra("title");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        collapsingToolbar.setTitle(title);
        collapsingToolbar.setExpandedTitleColor(Color.WHITE);
        collapsingToolbar.setCollapsedTitleTextColor(Color.WHITE);
//        toolbar.setNavigationIcon(R.drawable.ic_arrow);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        dbManager = new DBManager();
        db = dbManager.openDatabase(this);
        Cursor cursor = db.rawQuery("select * from islands where id = "+id,null);

        String cover,content,imgs;
        Island island = new Island();
        while (cursor.moveToNext()) {
            id = cursor.getInt(cursor.getColumnIndex("id"));
//            title = cursor.getString(cursor.getColumnIndex("title"));
            cover = cursor.getString(cursor.getColumnIndex("cover"));
            content = cursor.getString(cursor.getColumnIndex("content"));
            imgs = cursor.getString(cursor.getColumnIndex("pics"));
            island.setId(id);
            island.setTitle(title);
            island.setCover(cover);
            island.setContent(content);
            island.setPics(imgs);
//            System.out.println(title);
//            System.out.println(cover);
        }
        cursor.close();

        if (!island.getCover().isEmpty()) {
            Picasso.get().load(island.getCover()).into(ivCover);
        }else {
            Picasso.get().load(Constants.TEMP_URL).into(ivCover);
        }

        tvContent.setText(island.getContent());

        if (!island.getPics().isEmpty()){

            imglist = new ArrayList<>();
            fabPics.setVisibility(View.VISIBLE);
            String[] pics = island.getPics().split(",");
            for (String url : pics ){
                Log.i("Detailactivity", "onCreate: url "+url);
                imglist.add(url);
            }
        }

        fabPics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(DetailActivity.this,PhotoActivity.class);
                intent.putStringArrayListExtra("imglist", (ArrayList<String>) imglist);

                startActivity(intent);
            }
        });
    }

}
