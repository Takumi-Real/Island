package com.bao.island.ui.fragment;


import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
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
import com.bao.island.ui.adapter.HomeAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class MainFragment extends Fragment {


    Unbinder unbinder;
    @BindView(R.id.rec_main)
    RecyclerView recMain;
    HomeAdapter adapter;
    List<News> data;
    List<News> sliderList;

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        unbinder = ButterKnife.bind(this, view);

        new HomeTask().execute();

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @SuppressLint("StaticFieldLeak")
    class HomeTask extends AsyncTask<String,Integer,List<News>>{

        @Override
        protected List<News> doInBackground(String... strings) {
            sliderList = DataManager.getSliderList(Constants.HOME_PAGE);
//            Log.i("MainFragment", "doInBackground: "+sliderList.get(0).getTime());
            data = DataManager.getRecomendation(Constants.HOME_PAGE);
//            data.addAll(0,sliderList);
            return data;
        }

        @Override
        protected void onPostExecute(List<News> news) {
            super.onPostExecute(news);
            adapter = new HomeAdapter(getActivity(),news,sliderList);
            recMain.setLayoutManager(new LinearLayoutManager(getActivity()));
            recMain.setAdapter(adapter);
        }
    }
}
