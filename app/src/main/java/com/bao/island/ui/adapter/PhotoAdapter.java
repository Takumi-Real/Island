package com.bao.island.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;


import com.bao.island.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by BAO on 2018/4/30.
 */

public class PhotoAdapter extends PagerAdapter {
    private Context context;
    private List<String> imgs;


    public PhotoAdapter(Context context, List<String> imgs) {
        this.context = context;
        this.imgs = imgs;
    }

    @Override
    public int getCount() {
        return imgs==null?0:imgs.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        String url = imgs.get(position);
        System.out.println("position: "+position+"\nphotourl: "+url);
        PhotoView photoView = new PhotoView(context);
        photoView.setBackgroundColor(Color.BLACK);
        Picasso.get().load(url.trim()).into(photoView);

        container.addView(photoView);
//        photoView.setOnViewTapListener(new PhotoViewAttacher.OnViewTapListener() {
//            @Override
//            public void onViewTap(View view, float x, float y) {
//                Animation inanimation = AnimationUtils.loadAnimation(context, R.anim.down_in);
//                Animation outanimation = AnimationUtils.loadAnimation(context,R.anim.up_out);
//                if (toolbar.getVisibility() == View.INVISIBLE){
//                    toolbar.startAnimation(inanimation);
//                    toolbar.setVisibility(View.VISIBLE);
//
//                }else {
//                    toolbar.startAnimation(outanimation);
//                    toolbar.setVisibility(View.INVISIBLE);
//
//                }
//            }
//        });

        return photoView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
