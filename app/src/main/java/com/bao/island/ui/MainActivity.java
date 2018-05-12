package com.bao.island.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bao.island.R;
import com.bao.island.app.Constants;
import com.bao.island.ui.activity.FavorActivity;
import com.bao.island.ui.adapter.FavorAdapter;
import com.bao.island.ui.adapter.FragmentTabAdapter;
import com.bao.island.ui.fragment.MainFragment;
import com.bao.island.ui.fragment.NewsTabFragment;
import com.bao.island.ui.news.activity.NewsActivity;
import com.bao.island.ui.news.fragment.NewsFragment;
import com.bao.island.util.CacheUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    //    Button btn1;
    @BindView(R.id.main_toolbar)
    Toolbar mainToolbar;
    @BindView(R.id.tab_main)
    TabLayout tabMain;
    @BindView(R.id.vp_main)
    ViewPager vpMain;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    List<String> titles ;
    List<Fragment> fragments;
    FragmentTabAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();
        initListener();


    }

    private void initListener() {

        mainToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(Gravity.START);
            }
        });

        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_favor:
                        startActivity(new Intent(MainActivity.this, FavorActivity.class));
                        break;
                    case R.id.nav_cache:
                        showCacheDialog();
                        break;
                    case R.id.nav_share:
                        break;
                    default:
                        break;
                }
                drawerLayout.closeDrawers();
                return true;
            }
        });

    }

    private void initData() {
        titles = new ArrayList<>();
        titles.add("推荐");
        titles.add("新闻");
        titles.add("历史");
        NewsFragment historyFragment = new NewsFragment();
        Bundle historuBundle = new Bundle();
        historuBundle.putString(Constants.NEWS_TYPE,Constants.TYPE_HISTORY);
        historyFragment.setArguments(historuBundle);

        fragments = new ArrayList<>();
        fragments.add(new MainFragment());
        fragments.add(new NewsTabFragment());
        fragments.add(historyFragment);
        adapter = new FragmentTabAdapter(getSupportFragmentManager(),fragments,titles);
        vpMain.setAdapter(adapter);
        tabMain.setupWithViewPager(vpMain);


    }



    private void showCacheDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示")
                .setMessage("清除应用缓存?")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        CacheUtil.clearCache();
                        Toast.makeText(MainActivity.this, "清理缓存完成", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}
