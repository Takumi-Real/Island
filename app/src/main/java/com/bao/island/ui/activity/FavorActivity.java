package com.bao.island.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bao.island.R;
import com.bao.island.app.BaseActivity;
import com.bao.island.model.Favor;
import com.bao.island.model.News;
import com.bao.island.ui.adapter.FavorAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;

import org.litepal.crud.DataSupport;

import java.util.List;

import butterknife.BindView;

public class FavorActivity extends BaseActivity {

    @BindView(R.id.rec_favor)
    RecyclerView recFavor;


    FavorAdapter adapter;
    List<Favor> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_favor);
        recFavor.setLayoutManager(new LinearLayoutManager(this));
        list = DataSupport.findAll(Favor.class);
        adapter = new FavorAdapter(list);
        recFavor.setAdapter(adapter);


        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (list.get(position).getType()){
                    case 1:
                        break;
                    case 2:
                        break;
                }
            }
        });

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_favor;
    }



}
