package com.example.katalogfilm;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTvshow extends Fragment {
    private ArrayList<Tvshow> tvshows = new ArrayList<>();
    TvshowAdapter adapter;
    ProgressDialog progressDialog;
    ViewModelTvshow viewModelTvshow;
    public FragmentTvshow() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tvshow_fragment, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage(getString(R.string.app_name));
        if (tvshows.size() <= 0) {
            progressDialog.show();
        } else {
            progressDialog.dismiss();
        }
        viewModelTvshow = ViewModelProviders.of(this).get(ViewModelTvshow.class);
        viewModelTvshow.getTvshow().observe(this, getTvshow);
        viewModelTvshow.setListTvshow("");
        RecyclerView rvFragmentTvshows = view.findViewById(R.id.rv_fragmenttvshow);
        rvFragmentTvshows.setHasFixedSize(true);
        rvFragmentTvshows.setLayoutManager(new LinearLayoutManager(view.getContext()));
        adapter = new TvshowAdapter(view.getContext());
        adapter.setTvshows(tvshows);
        rvFragmentTvshows.setAdapter(adapter);
    }
    private Observer<ArrayList<Tvshow>> getTvshow = new Observer<ArrayList<Tvshow>>() {
        @Override
        public void onChanged(ArrayList<Tvshow> tvshowItems) {
            if (tvshows != null) {
                tvshows = tvshowItems;
                adapter.setData(tvshows);
            }
            progressDialog.dismiss();
        }
    };
}
