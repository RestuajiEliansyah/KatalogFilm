package com.example.katalogfilm;
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

import java.util.ArrayList;

public class TvshowAdapter extends RecyclerView.Adapter<TvshowAdapter.RecycleViewHolder> {

    private ArrayList<Tvshow> tvshows;
    private Context context;

    public TvshowAdapter(Context context) {
        this.context = context;
    }

    public ArrayList<Tvshow> getTvshows() {
        return tvshows;
    }

    public void setTvshows(ArrayList<Tvshow> tvshows) {
        this.tvshows = tvshows;
    }

    public void setData(ArrayList<Tvshow> items) {
        tvshows.clear();
        tvshows.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecycleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_tvshow, viewGroup, false);
        return new RecycleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewHolder recycleViewHolder, final int i) {
        recycleViewHolder.tvTitle.setText(tvshows.get(i).getName());
        recycleViewHolder.tvOverview.setText(tvshows.get(i).getOverview());
        Glide.with(context).load(tvshows.get(i).getPoster_path_string())
                .into(recycleViewHolder.imgPhoto);

        recycleViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    @Override
    public int getItemCount() {
        return tvshows.size();
    }

    public class RecycleViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        TextView tvOverview;
        ImageView imgPhoto;

        public RecycleViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.txt_tvshow);
            tvOverview = itemView.findViewById(R.id.txt_tvshow_description);
            imgPhoto = itemView.findViewById(R.id.img_tvshow);
        }
    }
}
