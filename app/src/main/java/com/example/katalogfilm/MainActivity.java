package com.example.katalogfilm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
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
        title = getResources().getString(R.string.txt_movie);
        BottomNavigationView navView = findViewById(R.id.bottom_navigation);
        fragmentManager = getSupportFragmentManager();
        movieFragment = new FragmentMovie();
        tvshowFragment = new FragmentTvshow();
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
                    case R.id.action_favorite:
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
}