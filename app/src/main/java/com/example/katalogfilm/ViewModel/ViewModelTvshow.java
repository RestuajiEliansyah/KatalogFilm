package com.example.katalogfilm.ViewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.katalogfilm.Parcelable.Tvshow;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class ViewModelTvshow extends ViewModel {
    private static final String URLFULL = "https://api.themoviedb.org/3/discover/tv?api_key=1b0b356a4b3d30fded43a1e672641b47&language=en-US";
    private MutableLiveData<ArrayList<Tvshow>> listTvshow = new MutableLiveData<>();

    public void setListTvshow(final String id) {
        final ArrayList<Tvshow> listItems = new ArrayList<>();

        AsyncHttpClient client = new AsyncHttpClient();
        String url = URLFULL;
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("results");
                    Log.d("Trying TvShowVM", "Trying");

                    for (int i = 0; i < list.length(); i++) {
                        Log.d("Trying for loop", "for loop");

                        JSONObject tvshow = list.getJSONObject(i);
                        Log.d("Trying getJSONObject", "getJSONObject");

                        Tvshow tvshowItem = new Tvshow(tvshow);
                        Log.d("Trying new TVshow", "new TVshow");

                        listItems.add(tvshowItem);
                        Log.d("List TvShowVM ke" + i, tvshowItem.getPoster_path_string());
                    }

                    listTvshow.postValue(listItems);

                } catch (Exception e) {
                    Log.d("Exception TvShowVM", e.getMessage());
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("onFailure", error.getMessage());
            }
        });
    }

    public LiveData<ArrayList<Tvshow>> getTvshow() {
        return listTvshow;
    }
}
