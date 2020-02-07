package com.example.katalogfilm.DetailActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.katalogfilm.Database.Movie.Tvshow.TvshowHelper;
import com.example.katalogfilm.R;
import com.example.katalogfilm.Parcelable.Tvshow;

import java.util.ArrayList;

public class DetailTvshow extends AppCompatActivity {
    public static String DETAIL_TVSHOW_EXTRA = "detail tv show extra";
    private Tvshow tvshow;
    private TextView title;
    private TextView voteAverage;
    private TextView date;
    private TextView overview;
    private ImageView imgPoster;
    private Menu menu;
    private boolean isFav;
    private TvshowHelper tvshowHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tvshow);
        title = findViewById(R.id.title_tvshow);
        overview = findViewById(R.id.detail_tvshow);
        tvshowHelper = TvshowHelper.getInstance(getApplicationContext());
        tvshowHelper.open();
        voteAverage = findViewById(R.id.rate_tvshow);
        date = findViewById(R.id.calender_tvshow);
        tvshow = getIntent().getParcelableExtra(DETAIL_TVSHOW_EXTRA);
        imgPoster = findViewById(R.id.image_tvshow);
        setDetailTvshow();
        isFav = false;
        checkFav();
    }
    private void setDetailTvshow() {
        if (tvshow != null){
            setTitle(R.string.tvshow);
            title.setText(tvshow.getName());
            voteAverage.setText(String.valueOf(tvshow.getVote_average()));
            date.setText(tvshow.getFirst_air_date());
            overview.setText(tvshow.getOverview());
            Glide.with(getApplicationContext()).load(tvshow.getPoster_path_string()).into(imgPoster);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bottom_detail, menu);
        this.menu = menu;
        setFav();
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_item_add_favorite_menu_detail) {
            favPressed();
        }
        return super.onOptionsItemSelected(item);
    }
    public void checkFav()
    {
        ArrayList<Tvshow> tvshowsInDatabase = tvshowHelper.getAllTvshows();
        for (Tvshow tvshow: tvshowsInDatabase){
            if (this.tvshow.getId() == tvshow.getId()){
                isFav = true;
            }
            if (isFav == true) {
                break;
            }
        }
    }
    public void setFav()
    {
        if (isFav) {
            menu.getItem(0).setIcon(getResources().getDrawable(R.drawable.star_add));
        } else {
            menu.getItem(0).setIcon(getResources().getDrawable(R.drawable.star_added));
        }
    }
    private void favPressed()
    {
        if(isFav)
        {
            Toast.makeText(this, "Favorite Tvshow has unadded", Toast.LENGTH_SHORT).show();
            tvshowHelper.deleteTvshow(tvshow.getId());
        }
        else
        {
            Toast.makeText(this, "Favorite Tvshow has added", Toast.LENGTH_SHORT).show();
            tvshowHelper.insertTvshow(tvshow);
        }
        isFav = !isFav;
        setFav();
    }
}