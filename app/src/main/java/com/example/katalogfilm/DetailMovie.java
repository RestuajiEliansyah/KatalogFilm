package com.example.katalogfilm;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class DetailMovie extends AppCompatActivity {
    private TextView txtTitle,txtVoteAverage,txtDate,txtOverview;
    private ImageView imgPoster;
    private Boolean isFavorite;
    private Menu menu;
    private Movie movie;
    public static String DETAIL_MOVIE_EXTRA = "Detail Movie Extra";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        txtTitle = findViewById(R.id.title_movie);
        txtOverview = findViewById(R.id.detail_movie);
        txtVoteAverage = findViewById(R.id.rate_movie);
        txtDate = findViewById(R.id.calender_movie);
        movie = getIntent().getParcelableExtra(DETAIL_MOVIE_EXTRA);
        imgPoster = findViewById(R.id.image_movie);
        setDetailMovie();
        isFavorite = false;
    }
    public void setDetailMovie()
    {
        if(movie !=null)
        {
            setTitle(R.string.txt_movie);
            txtTitle.setText(movie.getTitle());
            txtVoteAverage.setText(String.valueOf(movie.getVote_average()));
            txtDate.setText(movie.getRelease_date());
            txtOverview.setText(movie.getOverview());
            Glide.with(getApplicationContext()).load(movie.getPoster_path_string()).into(imgPoster);
        }
        else
        {
        }
    }
}
