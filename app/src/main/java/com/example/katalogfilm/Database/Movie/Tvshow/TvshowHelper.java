package com.example.katalogfilm.Database.Movie.Tvshow;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.katalogfilm.Parcelable.Tvshow;

import java.util.ArrayList;
import static com.example.katalogfilm.Database.Movie.Tvshow.TvshowDatabaseContract.TvshowColumns.BACKDROP_PATH_STRING;
import static com.example.katalogfilm.Database.Movie.Tvshow.TvshowDatabaseContract.TvshowColumns.FIRST_AIR_DATE;
import static com.example.katalogfilm.Database.Movie.Tvshow.TvshowDatabaseContract.TvshowColumns.ID;
import static com.example.katalogfilm.Database.Movie.Tvshow.TvshowDatabaseContract.TvshowColumns.TVSHOW_TABLE_NAME;
import static com.example.katalogfilm.Database.Movie.Tvshow.TvshowDatabaseContract.TvshowColumns.OVERVIEW;
import static com.example.katalogfilm.Database.Movie.Tvshow.TvshowDatabaseContract.TvshowColumns.NAME;
import static com.example.katalogfilm.Database.Movie.Tvshow.TvshowDatabaseContract.TvshowColumns.POSTER_PATH_STRING;
import static com.example.katalogfilm.Database.Movie.Tvshow.TvshowDatabaseContract.TvshowColumns.VOTE_AVERAGE;

public class TvshowHelper {

    private static final String DATABASE_TABLE = TVSHOW_TABLE_NAME;
    private static TvshowDatabaseHelper tvshowDatabaseHelper;
    private static TvshowHelper INSTANCE;

    private static SQLiteDatabase database;

    private TvshowHelper(Context context) {
        tvshowDatabaseHelper = new TvshowDatabaseHelper(context);
    }

    public static TvshowHelper getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (SQLiteOpenHelper.class) {
                if (INSTANCE == null) {
                    INSTANCE = new TvshowHelper(context);
                }
            }
        }
        return INSTANCE;
    }

    public void open() throws SQLException {
        database = tvshowDatabaseHelper.getWritableDatabase();
    }

    public void close() {
        tvshowDatabaseHelper.close();

        if (database.isOpen())
            database.close();
    }

    public ArrayList<Tvshow> getAllTvshows() {
        ArrayList<Tvshow> arrayList = new ArrayList<>();
        Cursor cursor = database.query(DATABASE_TABLE, null,
                null,
                null,
                null,
                null,
                ID + " ASC",
                null);
        cursor.moveToFirst();
        Tvshow tvshow;
        if (cursor.getCount() > 0) {
            do {
                tvshow = new Tvshow();
                tvshow.setId(cursor.getInt(cursor.getColumnIndexOrThrow(ID)));
                tvshow.setName(cursor.getString(cursor.getColumnIndexOrThrow(NAME)));
                tvshow.setOverview(cursor.getString(cursor.getColumnIndexOrThrow(OVERVIEW)));
                tvshow.setFirst_air_date(cursor.getString(cursor.getColumnIndexOrThrow(FIRST_AIR_DATE)));
                tvshow.setVote_average(cursor.getDouble(cursor.getColumnIndexOrThrow(VOTE_AVERAGE)));
                tvshow.setPoster_path_string(cursor.getString(cursor.getColumnIndexOrThrow(POSTER_PATH_STRING)));
                tvshow.setBackdrop_path_string(cursor.getString(cursor.getColumnIndexOrThrow(BACKDROP_PATH_STRING)));

                arrayList.add(tvshow);
                cursor.moveToNext();

            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public long insertTvshow(Tvshow tvshow) {
        ContentValues args = new ContentValues();
        args.put(ID, tvshow.getId());
        args.put(NAME, tvshow.getName());
        args.put(OVERVIEW, tvshow.getOverview());
        args.put(FIRST_AIR_DATE, tvshow.getFirst_air_date());
        args.put(VOTE_AVERAGE, tvshow.getVote_average());
        args.put(POSTER_PATH_STRING, tvshow.getPoster_path_string());
        args.put(BACKDROP_PATH_STRING, tvshow.getBackdrop_path_string());

        return database.insert(DATABASE_TABLE, null, args);
    }

    public int deleteTvshow(int id) {
        return database.delete(TVSHOW_TABLE_NAME, ID + " = '" + id + "'", null);
    }
}
