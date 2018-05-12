package com.bao.island.ui.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bao.island.R;
import com.bao.island.model.News;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by BAO on 2018/5/10.
 */

public class SliderAdapter extends StaticPagerAdapter {

    private List<String> pics = Arrays.asList("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1525979009893&di=964696679ff5bc7ac87624187353893a&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2F8718367adab44aedad9a0930b91c8701a08bfb0a.jpg",
            "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1476451385,2408244216&fm=200&gp=0.jpg",
            "http://e.hiphotos.baidu.com/zhidao/pic/item/a6efce1b9d16fdfa9325a2bab68f8c5494ee7b1c.jpg",
            "http://img5q.duitang.com/uploads/item/201505/15/20150515205520_iWF2U.jpeg");



    private List<News> list;

    public SliderAdapter(List<News> list) {
        this.list = list;
    }

    @Override
    public View getView(ViewGroup container, int position) {
        ImageView view = new ImageView(container.getContext());

//        view.setImageResource(imgs[position]);
        view.setScaleType(ImageView.ScaleType.CENTER_CROP);

        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        Picasso.get().load(list.get(position).getTime()).into(view);
        System.out.println("imgurl: "+list.get(position).getTitle());
        return view;
    }

    @Override
    public int getCount() {
        return pics.size();
    }
}
