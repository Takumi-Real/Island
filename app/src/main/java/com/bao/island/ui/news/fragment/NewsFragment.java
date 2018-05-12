package com.bao.island.ui.news.fragment;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bao.island.R;
import com.bao.island.app.Constants;
import com.bao.island.model.News;
import com.bao.island.model.DataManager;
import com.bao.island.ui.activity.NewsDetailActivity;
import com.bao.island.ui.adapter.NewsAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment {


    List<News> newsList;
    String type;
    RecyclerView recNews;
    NewsAdapter adapter;
    private View view;

    Handler handler;

    public NewsFragment() {
        // Required empty public constructor
    }


    @SuppressLint("HandlerLeak")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        view = inflater.inflate(R.layout.fragment_base, container, false);
        recNews = view.findViewById(R.id.rec_news);
        type = getArguments().getString(Constants.NEWS_TYPE);

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                newsList = (List<News>) msg.obj;
                adapter = new NewsAdapter(newsList);

                recNews.setLayoutManager(new LinearLayoutManager(getActivity()));

                recNews.setAdapter(adapter);

                adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        Intent intent = new Intent();
                        intent.putExtra("title", newsList.get(position).getTitle());
                        intent.putExtra("url", newsList.get(position).getHref().trim());
                        intent.setClass(getActivity(), NewsDetailActivity.class);
                        startActivity(intent);
                    }
                });
            }
        };
        new Thread(new Runnable() {
            @Override
            public void run() {
                String url = Constants.BASE_URL + type;
                List<News> list = new ArrayList<>();
                list = DataManager.getNewsList(url);
//                    System.out.println(list.get(0).getTitle());
                Message message = new Message();
                message.obj = list;
                handler.sendMessage(message);
            }
        }).start();


        return view;
    }


}
