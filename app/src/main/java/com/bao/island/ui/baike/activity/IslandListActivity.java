package com.bao.island.ui.baike.activity;

import android.content.ClipData;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bao.island.R;
import com.bao.island.app.BaseActivity;
import com.bao.island.model.Island;
import com.bao.island.ui.adapter.IslandsAdapter;
import com.bao.island.util.DBManager;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IslandListActivity extends BaseActivity {
    DBManager dbManager;
    SQLiteDatabase db;
    @BindView(R.id.rec_list)
    RecyclerView recList;
    IslandsAdapter adapter;
    List<Island> islands;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_list);
        ButterKnife.bind(this);
        dbManager = new DBManager();
        db = dbManager.openDatabase(this);


        islands = new ArrayList<>();
        recList.setLayoutManager(new GridLayoutManager(this, 2));

        int type = getIntent().getIntExtra("type", 1);
        String toolbarTitle = "";
        switch (type) {
            case 1:
                toolbarTitle = "东沙群岛";
                break;
            case 2:
                toolbarTitle = "西沙群岛";
                break;
            case 3:
                toolbarTitle = "中沙群岛";
                break;
            case 4:
                toolbarTitle = "南沙群岛";
                break;
            default:
                break;
        }
        getToolbar().setTitle(toolbarTitle);
//        Cursor cursor = db.rawQuery("select * from islands where type = ? ", new String[]{"1"});
        Cursor cursor = db.rawQuery("select * from islands where type = " + type, null);

        String title, cover;
        int id;
        while (cursor.moveToNext()) {
            id = cursor.getInt(cursor.getColumnIndex("id"));
            title = cursor.getString(cursor.getColumnIndex("title"));
            cover = cursor.getString(cursor.getColumnIndex("cover"));
            Island i = new Island();
            i.setId(id);
            i.setTitle(title);
            i.setCover(cover);
            islands.add(i);
//            System.out.println(title);
//            System.out.println(cover);
        }
        cursor.close();
        adapter = new IslandsAdapter(islands);
        recList.setAdapter(adapter);


        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                int id = islands.get(position).getId();
                Intent intent = new Intent();
                intent.putExtra("id", id);
                intent.putExtra("title",islands.get(position).getTitle());
                intent.setClass(IslandListActivity.this, DetailActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_list;
    }
}
