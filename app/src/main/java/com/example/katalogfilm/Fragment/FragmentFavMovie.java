package com.example.katalogfilm.Fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.katalogfilm.Adapter.FavMovieAdapter;
import com.example.katalogfilm.Database.Movie.MovieHelper;
import com.example.katalogfilm.Parcelable.Movie;
import com.example.katalogfilm.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentFavMovie extends Fragment {
    private ArrayList<Movie> movies = new ArrayList<>();
    private MovieHelper movieHelper;
    private FavMovieAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fav_movie, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        movieHelper = MovieHelper.getInstance(view.getContext());
        movieHelper.open();
        RecyclerView rvFavoriteFragmentMovies = view.findViewById(R.id.rv_fragment_favmovie);
        rvFavoriteFragmentMovies.setHasFixedSize(true);
        rvFavoriteFragmentMovies.setLayoutManager(new LinearLayoutManager(view.getContext()));
        adapter = new FavMovieAdapter(view.getContext());
        adapter.setMovies(movies);
        rvFavoriteFragmentMovies.setAdapter(adapter);
    }
    @Override
    public void onResume() {
        super.onResume();
        movies = movieHelper.getAllMovies();
        adapter.setData(movies);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        movieHelper.close();
    }
}
