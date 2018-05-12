package com.bao.island.ui.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bao.island.R;
import com.bao.island.app.Constants;
import com.bao.island.ui.adapter.FragmentTabAdapter;
import com.bao.island.ui.news.fragment.NewsFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsTabFragment extends Fragment {


    @BindView(R.id.tab_news)
    TabLayout tabNews;
    @BindView(R.id.vp_news)
    ViewPager vpNews;
    Unbinder unbinder;

    List<Fragment> fragments;
    List<String> titles;
    FragmentTabAdapter adapter;
    Fragment dongshaFragment;
    Fragment xishaFragment;
    Fragment zhongshaFragment;
    Fragment nanshaFragment;

    public NewsTabFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news_tab, container, false);

        unbinder = ButterKnife.bind(this, view);
        initData();
        return view;
    }


    private void initData() {

        fragments = new ArrayList<>();
        dongshaFragment = new NewsFragment();
        xishaFragment = new NewsFragment();
        zhongshaFragment = new NewsFragment();
        nanshaFragment = new NewsFragment();

        Bundle dongshaBundle = new Bundle();
        dongshaBundle.putString(Constants.NEWS_TYPE,Constants.TYPE_DONG);
        dongshaFragment.setArguments(dongshaBundle);

        Bundle xishaBundle = new Bundle();
        xishaBundle.putString(Constants.NEWS_TYPE,Constants.TYPE_XI);
        xishaFragment.setArguments(xishaBundle);

        Bundle zhongshaBundle = new Bundle();
        zhongshaBundle.putString(Constants.NEWS_TYPE,Constants.TYPE_ZHONG);
        zhongshaFragment.setArguments(zhongshaBundle);

        Bundle nanshaBundle = new Bundle();
        nanshaBundle.putString(Constants.NEWS_TYPE,Constants.TYPE_NAN);
        nanshaFragment.setArguments(nanshaBundle);

        fragments.add(dongshaFragment);
        fragments.add(xishaFragment);
        fragments.add(zhongshaFragment);
        fragments.add(nanshaFragment);



        titles = new ArrayList<>();
        titles.add("东沙群岛");
        titles.add("西沙群岛");
        titles.add("中沙群岛");
        titles.add("南沙群岛");

        adapter = new FragmentTabAdapter(getChildFragmentManager(),fragments,titles);

        vpNews.setAdapter(adapter);
        tabNews.setupWithViewPager(vpNews);


    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
