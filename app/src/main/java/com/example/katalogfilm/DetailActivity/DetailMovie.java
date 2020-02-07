package com.example.katalogfilm.DetailActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.katalogfilm.Database.Movie.MovieHelper;
import com.example.katalogfilm.Parcelable.Movie;
import com.example.katalogfilm.R;

import java.util.ArrayList;

public class DetailMovie extends AppCompatActivity {
    private TextView title,txtVoteAverage,txtDate, overview;
    private ImageView imgPoster;
    private Boolean isFav;
    private MovieHelper movieHelper;
    private Menu menu;
    private Movie movie;
    public static String DETAIL_MOVIE_EXTRA = "DETAIL MOVIE EXTRA";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        title = findViewById(R.id.title_movie);
        overview = findViewById(R.id.detail_movie);
        movieHelper = MovieHelper.getInstance(getApplicationContext());
        movieHelper.open();
        txtVoteAverage = findViewById(R.id.rate_movie);
        txtDate = findViewById(R.id.calender_movie);
        movie = getIntent().getParcelableExtra(DETAIL_MOVIE_EXTRA);
        imgPoster = findViewById(R.id.image_movie);
        setDetailMovie();
        isFav = false;
        checkFav();
    }
    public void setDetailMovie()
    {
        if(movie !=null)
        {
            setTitle(R.string.txt_movie);
            title.setText(movie.getTitle());
            txtVoteAverage.setText(String.valueOf(movie.getVote_average()));
            txtDate.setText(movie.getRelease_date());
            overview.setText(movie.getOverview());
            Glide.with(getApplicationContext()).load(movie.getPoster_path_string()).into(imgPoster);
        }
    }
    public void checkFav()
    {
        ArrayList<Movie> moviesInDatabase = movieHelper.getAllMovies();
        for (Movie movie: moviesInDatabase){
            if (this.movie.getId() == movie.getId()){
                isFav = true;
            }
            if (isFav == true) {
                break;
            }
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
    private void favPressed()
    {
        if(isFav)
        {
            Toast.makeText(this, "Favorite Movie has unadded", Toast.LENGTH_SHORT).show();
            movieHelper.deleteMovie(movie.getId());
        }
        else
        {
            Toast.makeText(this, "Favorite Movie has added", Toast.LENGTH_SHORT).show();
            movieHelper.insertMovie(movie);
        }
        isFav = !isFav;
        setFav();
    }
    public void setFav()
    {
        if (isFav) {
            menu.getItem(0).setIcon(getResources().getDrawable(R.drawable.star_add));
        } else {
            menu.getItem(0).setIcon(getResources().getDrawable(R.drawable.star_added));
        }
    }
}
