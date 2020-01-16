package com.example.katalogfilm;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.RecycleViewHolder> {
    private Context context;
    private ArrayList<Movie> movies;
    public class RecycleViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvTitle,tvOverview;
        ImageView imgPhoto;
        public RecycleViewHolder(@NonNull View itemView)
        {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.txt_movie);
            tvOverview = itemView.findViewById(R.id.txt_movie_description);
            imgPhoto = itemView.findViewById(R.id.img_movie);
        }
    }
    public ArrayList<Movie> getMovies()
    {
        return movies;
    }
    public void setMovies(ArrayList<Movie> movies)
    {
        this.movies = movies;
    }
    public void setData(ArrayList<Movie> items)
    {
        movies.clear();
        movies.addAll(items);
        notifyDataSetChanged();
    }
    public MovieAdapter(Context context)
    {
        this.context = context;
    }
    @NonNull
    @Override
    public RecycleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_movie,viewGroup,false);
        return new RecycleViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull final RecycleViewHolder recycleViewHolder, final int i) {
        recycleViewHolder.tvOverview.setText(movies.get(i).getOverview());
        recycleViewHolder.tvTitle.setText(movies.get(i).getTitle());
        Glide.with(context).load(movies.get(i).getPoster_path_string())
                .into(recycleViewHolder.imgPhoto);
        recycleViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
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
}
