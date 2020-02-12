package com.example.katalogfilm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.katalogfilm.Fragment.FragmentFavMovie;
import com.example.katalogfilm.Fragment.FragmentFavTvshow;
import com.example.katalogfilm.Fragment.FragmentMovie;
import com.example.katalogfilm.Fragment.FragmentTvshow;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private FragmentFavMovie favMovieFragment;
    private FragmentFavTvshow favTvshowFragment;
    private FragmentMovie movieFragment;
    private FragmentTvshow tvshowFragment;
    private String title;
    private final String STATE_TITLE = "state_string";
    private final String STATE_MODE = "state_mode";
    private int mode = R.id.action_movie ;
    private int mode2 = R.id.action_tvshow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        title = getResources().getString(R.string.movie);
        BottomNavigationView navView = findViewById(R.id.bottom_navigation);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        fragmentManager = getSupportFragmentManager();
        movieFragment = new FragmentMovie();
        tvshowFragment = new FragmentTvshow();
        favTvshowFragment = new FragmentFavTvshow();
        favMovieFragment = new FragmentFavMovie();
        if (savedInstanceState == null)
        {
            setToMovieFragment();
        }
        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_movie:
                        setToMovieFragment();
                        break;
                    case R.id.action_tvshow:
                        setToTvshowFragment();
                        break;
                    case R.id.action_fav_movies:
                        setToFavMovie();
                        break;
                    case R.id.action_fav_tvshows:
                        setToFavTvshow();
                        break;
                }
                return true;
            }
        });
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE_MODE, mode);
        outState.putString(STATE_TITLE, title);
    }
    public void setToFavTvshow()
    {
        setTitle(title);
        fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = fragmentManager.findFragmentByTag(FragmentFavTvshow.class.getSimpleName());
        if (!(fragment instanceof FragmentFavTvshow)) {
            fragmentTransaction.replace(
                    R.id.relative_layout_container,
                    favTvshowFragment,
                    FragmentFavTvshow.class.getSimpleName()
            );
            fragmentTransaction.commit();
        }
    }
    public void setToFavMovie()
    {
        setTitle(title);
        fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = fragmentManager.findFragmentByTag(FragmentFavMovie.class.getSimpleName());
        if (!(fragment instanceof FragmentFavMovie)) {
            fragmentTransaction.replace(
                    R.id.relative_layout_container,
                    favMovieFragment,
                    FragmentFavMovie.class.getSimpleName()
            );
            fragmentTransaction.commit();
        }
    }
    private void setToMovieFragment() {
        setTitle(title);
        fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = fragmentManager.findFragmentByTag(FragmentMovie.class.getSimpleName());
        if (!(fragment instanceof FragmentMovie)) {
            fragmentTransaction.replace(
                    R.id.relative_layout_container,
                    movieFragment,
                    FragmentMovie.class.getSimpleName()
            );
            fragmentTransaction.commit();
        }
    }
    /*private void setToMovieFragment() {
        setTitle(title);
        fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = fragmentManager.findFragmentByTag(FragmentMovie.class.getSimpleName());
        if (!(fragment instanceof FragmentMovie)) {
            fragmentTransaction.replace(
                    R.id.relative_layout_container,
                    movieFragment,
                    FragmentMovie.class.getSimpleName()
            );
            fragmentTransaction.commit();
        }
    }*/
    private void setToTvshowFragment() {
        setTitle("Tv Show");
        fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = fragmentManager.findFragmentByTag(FragmentTvshow.class.getSimpleName());
        if (!(fragment instanceof FragmentTvshow)) {
            fragmentTransaction.replace(
                    R.id.relative_layout_container,
                    tvshowFragment,
                    FragmentMovie.class.getSimpleName()
            );
            fragmentTransaction.commit();
        }
    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_movie:
                    title = getResources().getString(R.string.movie);
                    mode = item.getItemId();
                    setToMovieFragment();
                    return true;
                case R.id.action_tvshow:
                    title = getResources().getString(R.string.tvshow);
                    mode = item.getItemId();
                    setToTvshowFragment();
                    return true;
                case R.id.action_fav_movies:
                    title = getResources().getString(R.string.favorite);
                    mode = item.getItemId();
                    setToFavMovie();
                    return true;
                case R.id.action_fav_tvshows:
                    title = getResources().getString(R.string.favorite_tvshow);
                    mode = item.getItemId();
                    setToFavTvshow();
                    return true;
            }
            return false;
        }
    };
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_change_settings) {
            Intent mIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(mIntent);
        }
        return super.onOptionsItemSelected(item);
    }
}