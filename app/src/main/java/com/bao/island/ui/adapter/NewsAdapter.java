package com.bao.island.ui.adapter;

import android.support.annotation.Nullable;

import com.bao.island.R;
import com.bao.island.model.News;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by BAO on 2018/5/4.
 */

public class NewsAdapter extends BaseQuickAdapter<News,BaseViewHolder> {


    public NewsAdapter(@Nullable List<News> data) {
        super(R.layout.item_news,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, News item) {
        helper.setText(R.id.tv_news_title,item.getTitle());
        helper.setText(R.id.tv_news_time,item.getTime());
//        System.out.println(item.getHref());
//        System.out.println("newstitle: "+item.getTitle());
    }


}
