package com.example.katalogfilm.Database.Movie.Tvshow;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.katalogfilm.Database.Movie.Tvshow.TvshowDatabaseContract;
import static com.example.katalogfilm.Database.Movie.Tvshow.TvshowDatabaseContract.TvshowColumns.TVSHOW_TABLE_NAME;

public class TvshowDatabaseHelper extends SQLiteOpenHelper {
    private static final String TVSHOW_DATABASE_NAME = "dbtvshow";

    private static final int TVSHOW_DATABASE_VERSION = 1;

    private static final String SQL_CREATE_TABLE_TVSHOW = String.format("CREATE TABLE %s" +
                    " (%s TEXT NULL," +
                    " %s TEXT NULL," +
                    " %s TEXT NULL," +
                    " %s TEXT NULL," +
                    " %s TEXT NULL," +
                    " %s TEXT NULL," +
                    " %s TEXT NULL)",
            TVSHOW_TABLE_NAME,
            TvshowDatabaseContract.TvshowColumns.ID,
            TvshowDatabaseContract.TvshowColumns.NAME,
            TvshowDatabaseContract.TvshowColumns.OVERVIEW,
            TvshowDatabaseContract.TvshowColumns.FIRST_AIR_DATE,
            TvshowDatabaseContract.TvshowColumns.VOTE_AVERAGE,
            TvshowDatabaseContract.TvshowColumns.POSTER_PATH_STRING,
            TvshowDatabaseContract.TvshowColumns.BACKDROP_PATH_STRING
    );

    TvshowDatabaseHelper(Context context) {
        super(context, TVSHOW_DATABASE_NAME, null, TVSHOW_DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_TVSHOW);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TVSHOW_TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
