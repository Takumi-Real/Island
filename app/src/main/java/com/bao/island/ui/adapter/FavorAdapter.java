package com.bao.island.ui.adapter;

import android.widget.ImageView;

import com.bao.island.R;
import com.bao.island.app.Constants;
import com.bao.island.model.Favor;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by BAO on 2018/5/12.
 */

public class FavorAdapter extends BaseMultiItemQuickAdapter<Favor,BaseViewHolder> {


    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public FavorAdapter(List<Favor> data) {
        super(data);
        addItemType(Constants.TYPE_NEWS, R.layout.item_news);
        addItemType(Constants.TYPE_IMG, R.layout.item_island);
    }



    @Override
    protected void convert(BaseViewHolder helper, Favor item) {
        switch (item.getItemType()){
            case 1:
                helper.setText(R.id.tv_news_title,item.getTitle());
                break;
            case 2:
                Picasso.get().load(item.getUrl()).into((ImageView) helper.getView(R.id.island_cover));
                break;
        }

    }
}
