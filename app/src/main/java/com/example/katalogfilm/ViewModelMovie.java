package com.example.katalogfilm;

import android.os.Parcelable;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import org.json.JSONArray;
import org.json.JSONObject;
import java.lang.reflect.Array;
import java.util.ArrayList;
import cz.msebera.android.httpclient.Header;
public class ViewModelMovie extends ViewModel {
    private MutableLiveData<ArrayList<Movie>> listMovie = new MutableLiveData<>();
    private static final String URLFULL= Web.URL_MOVIE_AND_TV_SHOW
            + Web.URL_MOVIES_DISCOVER
            + "?api_key="
            + Web.API +
            "&language=en-US";
    public void setMovies(){
        String url=URLFULL;
        final ArrayList<Movie> listItem = new ArrayList<>();
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("results");
                    for (int i = 0; i < list.length(); i++) {
                        JSONObject movie = list.getJSONObject(i);
                        Movie movieItems = new Movie(movie);
                        listItem.add(movieItems);
                    }
                    listMovie.postValue(listItem);
                } catch (Exception e) {
                    Log.d("Exception", e.getMessage());
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("onFailure", error.getMessage());
            }
        });
    }
    public LiveData<ArrayList<Movie>> getMovies()
    {
        return listMovie;
    }
}
