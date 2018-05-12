package com.bao.island.ui.adapter;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bao.island.R;
import com.bao.island.model.News;
import com.bao.island.ui.baike.activity.IslandListActivity;
import com.jude.rollviewpager.RollPagerView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by BAO on 2018/5/10.
 */

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int ITEM_SLIDER = 1;
    private static final int ITEM_CATEGORY = 2;
    private static final int ITEM_NEWS= 3;

    private Activity activity;
    private List<News> newsList;
    private List<News> sliderList;

    public HomeAdapter(Activity activity,List<News> newsList,List<News> sliderList) {

        this.activity = activity;
        this.newsList = newsList;
        this.sliderList = sliderList;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0){
            return ITEM_SLIDER;
        }else if (position == 1){
            return ITEM_CATEGORY;
        }else{
            return ITEM_NEWS;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        RecyclerView.ViewHolder holder = null;
        if (ITEM_SLIDER == viewType){
            View v = inflater.inflate(R.layout.layout_slider,parent,false);
            holder = new oneViewHolder(v);
        }else if (ITEM_CATEGORY == viewType){
            View v = inflater.inflate(R.layout.layout_category,parent,false);
            holder = new twoViewHolder(v);
        }else if (ITEM_NEWS == viewType){
            View v = inflater.inflate(R.layout.item_news,parent,false);
            holder = new threeViewHolder(v);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof oneViewHolder){
            ((oneViewHolder) holder).slider.setAdapter(new SliderAdapter(sliderList));
        }else if (holder instanceof twoViewHolder){
            ((twoViewHolder) holder).iv1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.putExtra("type",1);
                    intent.setClass(activity,IslandListActivity.class);
                    activity.startActivity(intent);
                }
            });

        }else if (holder instanceof threeViewHolder){

            ((threeViewHolder) holder).tvTitle.setText(newsList.get(position).getTitle());
            ((threeViewHolder) holder).tvTime.setText(newsList.get(position).getTime());
        }
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }


    class oneViewHolder extends RecyclerView.ViewHolder{

        RollPagerView slider;
        public oneViewHolder(View itemView) {
            super(itemView);
            slider = itemView.findViewById(R.id.main_slider);
        }
    }


    class twoViewHolder extends RecyclerView.ViewHolder{

        CircleImageView iv1;
        CircleImageView iv2;
        CircleImageView iv3;
        CircleImageView iv4;
        public twoViewHolder(View itemView) {
            super(itemView);
            iv1 = itemView.findViewById(R.id.iv_one);
            iv2 = itemView.findViewById(R.id.iv_two);
            iv3 = itemView.findViewById(R.id.iv_three);
            iv4 = itemView.findViewById(R.id.iv_four);
            
        }
    }


    class threeViewHolder extends RecyclerView.ViewHolder{

        TextView tvTitle;
        TextView tvTime;
        public threeViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_news_title);
            tvTime = itemView.findViewById(R.id.tv_news_time);
        }
    }
}
