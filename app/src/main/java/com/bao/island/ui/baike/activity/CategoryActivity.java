package com.bao.island.ui.baike.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.bao.island.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CategoryActivity extends AppCompatActivity {

    @BindView(R.id.nansha)
    Button nansha;
    @BindView(R.id.xisha)
    Button xisha;
    @BindView(R.id.zhongsha)
    Button zhongsha;
    @BindView(R.id.dongsha)
    Button dongsha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.nansha, R.id.xisha, R.id.zhongsha, R.id.dongsha})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.nansha:
                Intent intent = new Intent(this, IslandListActivity.class);
                intent.putExtra("type",4);
                startActivity(intent);
                break;
            case R.id.xisha:
                Intent intent2 = new Intent(this, IslandListActivity.class);
                intent2.putExtra("type",2);
                startActivity(intent2);
                break;
            case R.id.zhongsha:
                Intent intent3 = new Intent(this, IslandListActivity.class);
                intent3.putExtra("type",3);
                startActivity(intent3);
                break;
            case R.id.dongsha:
                Intent intent4 = new Intent(this, IslandListActivity.class);
                intent4.putExtra("type",1);
                startActivity(intent4);
                break;
        }
    }


}
