package com.example.katalogfilm.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.katalogfilm.DetailActivity.DetailMovie;
import com.example.katalogfilm.Parcelable.Movie;
import com.example.katalogfilm.R;

import java.util.ArrayList;

public class FavMovieAdapter extends RecyclerView.Adapter<FavMovieAdapter.RecycleViewHolder> {
    private ArrayList<Movie> movies;
    private Context context;

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    public FavMovieAdapter(Context context) {
        this.context = context;
    }

    public void setData(ArrayList<Movie> items) {
        movies.clear();
        movies.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecycleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_fav_movie, viewGroup, false);
        return new FavMovieAdapter.RecycleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewHolder recycleViewHolder, final int i) {
        recycleViewHolder.title.setText(movies.get(i).getTitle());
        recycleViewHolder.overview.setText(movies.get(i).getOverview());
        Glide.with(context).load(movies.get(i).getPoster_path_string())
                .into(recycleViewHolder.photo);

        recycleViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailMovie.class);
                intent.putExtra(DetailMovie.DETAIL_MOVIE_EXTRA, getMovies().get(i));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class RecycleViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView overview;
        ImageView photo;

        public RecycleViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.txt_fav_movie);
            overview = itemView.findViewById(R.id.txt_fav_movie_description);
            photo = itemView.findViewById(R.id.img_fav_movie);
        }
    }
}