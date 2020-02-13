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
import com.example.katalogfilm.DetailActivity.DetailTvshow;
import com.example.katalogfilm.R;
import com.example.katalogfilm.Parcelable.Tvshow;

import java.util.ArrayList;

public class FavTvshowAdapter extends RecyclerView.Adapter<FavTvshowAdapter.RecycleViewHolder> {
    private ArrayList<Tvshow> tvshows;
    private Context context;
    public ArrayList<Tvshow> getTvshows() {
        return tvshows;
    }
    public void setTvshows(ArrayList<Tvshow> tvshows) {
        this.tvshows = tvshows;
    }
    public FavTvshowAdapter(Context context) {
        this.context = context;
    }
    public void setData(ArrayList<Tvshow> items) {
        tvshows.clear();
        tvshows.addAll(items);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public RecycleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_fav_tvshow, viewGroup, false);
        return new FavTvshowAdapter.RecycleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewHolder recycleViewHolder, final int i) {
        recycleViewHolder.title.setText(tvshows.get(i).getName());
        recycleViewHolder.overview.setText(tvshows.get(i).getOverview());
        recycleViewHolder.txtVoteAverage.setText(String.valueOf(tvshows.get(i).getVote_average()));
        Glide.with(context).load(tvshows.get(i).getPoster_path_string())
                .into(recycleViewHolder.photo);

        recycleViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailTvshow.class);
                intent.putExtra(DetailTvshow.DETAIL_TVSHOW_EXTRA, getTvshows().get(i));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tvshows.size();
    }

    public class RecycleViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView overview;
        ImageView photo;
        TextView txtVoteAverage;
        public RecycleViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.txt_fav_tvshow);
            overview = itemView.findViewById(R.id.txt_fav_tvshow_description);
            photo = itemView.findViewById(R.id.img_fav_tvshow);
            txtVoteAverage = itemView.findViewById(R.id.txt_rate_fav_movie);
        }
    }
}