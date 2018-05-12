package com.bao.island.ui.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bao.island.R;
import com.bao.island.app.Constants;
import com.bao.island.model.Island;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by BAO on 2018/4/29.
 */

public class IslandsAdapter extends BaseQuickAdapter<Island,BaseViewHolder> {
    public IslandsAdapter(@Nullable List<Island> data) {
        super(R.layout.item_island,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Island item) {

        String url = item.getCover();
        ImageView imageView = helper.getView(R.id.island_cover);
        if (!url.isEmpty()){
            System.out.println(url);
            Picasso.get().load(url).into(imageView);
        }else {
            Picasso.get().load(Constants.TEMP_URL).into(imageView);
        }
        helper.setText(R.id.island_title,item.getTitle());
    }
}
