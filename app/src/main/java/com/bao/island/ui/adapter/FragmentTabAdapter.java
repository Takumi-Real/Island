package com.bao.island.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.bao.island.app.Constants;

import java.util.List;

import javax.xml.transform.Source;

/**
 * Created by BAO on 2018/5/2.
 */

public class FragmentTabAdapter extends FragmentStatePagerAdapter {

    List<Fragment> fragments;
    List<String> titles;



    public FragmentTabAdapter(FragmentManager fm, List<Fragment> fragments, List<String> titles) {
        super(fm);
        this.fragments = fragments;
        this.titles = titles;
    }


    @Override
    public Fragment getItem(int position) {
//        System.out.println(fragments.get(position).getArguments().getString(Constants.NEWS_TYPE));
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments == null ? 0 : fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
