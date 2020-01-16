package com.example.katalogfilm;


import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.lifecycle.Observer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Array;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentMovie extends Fragment {
    public FragmentMovie() {
        // Required empty public constructor
    }
    private ViewModelMovie viewModelMovie;
    private MovieAdapter adapter;
    private ProgressDialog progressDialog;
    private ArrayList<Movie> movies = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.movie_fragment, container, false);
    }
    @Override
    public void onResume(){
        super.onResume();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage(getString(R.string.app_name));
        if (movies.size() <= 0)
        {
            progressDialog.show();
        }else
        {
            progressDialog.dismiss();
        }
        viewModelMovie = ViewModelProviders.of(this).get(ViewModelMovie.class);
        viewModelMovie.getMovies().observe(this, getMovie);
        viewModelMovie.setMovies();
        RecyclerView rvMoviesFragment = view.findViewById(R.id.rv_fragmentmovie);
        rvMoviesFragment.setHasFixedSize(true);
        rvMoviesFragment.setLayoutManager(new LinearLayoutManager(view.getContext()));
        adapter = new MovieAdapter(view.getContext());
        adapter.setMovies(movies);
        rvMoviesFragment.setAdapter(adapter);
    }
    private Observer<ArrayList<Movie>> getMovie = new Observer<ArrayList<Movie>>()
    {
        @Override
        public void onChanged(ArrayList<Movie> movieItems)
        {
            if (movieItems !=null)
            {
                movies = movieItems;
                adapter.setData(movieItems);
            }
            progressDialog.dismiss();
        }
    };
}