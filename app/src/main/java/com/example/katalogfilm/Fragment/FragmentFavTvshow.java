package com.example.katalogfilm.Fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.katalogfilm.Adapter.FavTvshowAdapter;
import com.example.katalogfilm.Database.Movie.Tvshow.TvshowHelper;
import com.example.katalogfilm.R;
import com.example.katalogfilm.Parcelable.Tvshow;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentFavTvshow extends Fragment {
    private ArrayList<Tvshow> tvshows= new ArrayList<>();
    private TvshowHelper tvshowHelper;
    private FavTvshowAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fav_tvshow, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvshowHelper = TvshowHelper.getInstance(view.getContext());
        tvshowHelper.open();
        RecyclerView rvFavoriteFragmentTvshows= view.findViewById(R.id.rv_fragment_favtvshow);
        rvFavoriteFragmentTvshows.setHasFixedSize(true);
        rvFavoriteFragmentTvshows.setLayoutManager(new LinearLayoutManager(view.getContext()));
        adapter = new FavTvshowAdapter(view.getContext());
        adapter.setTvshows(tvshows);
        rvFavoriteFragmentTvshows.setAdapter(adapter);
    }
    @Override
    public void onResume() {
        super.onResume();
        tvshows = tvshowHelper.getAllTvshows();
        adapter.setData(tvshows);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        tvshowHelper.close();
    }
}
