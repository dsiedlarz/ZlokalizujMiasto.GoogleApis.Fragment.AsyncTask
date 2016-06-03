package com.dsiedlarz.zlokalizujmiasto;

import android.os.AsyncTask;
import android.util.Log;

import com.dsiedlarz.zlokalizujmiasto.GeocodeResponse.*;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Dawid on 31.05.2016.
 */
public class Downloader extends AsyncTask<String, Void,GeocodeResponse> {

    public static final String API_URL = "http://maps.googleapis.com";





    public interface GoogleApis {
        @GET("/maps/api/geocode/json?sensor=true")
        Call<GeocodeResponse> site(@Query("address") String city);

    }

    @Override
    protected GeocodeResponse doInBackground(String... params) {


        // Create a very simple REST adapter which points the GitHub API.
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create an instance of our GitHub API interface.
       GoogleApis googleApis = retrofit.create(GoogleApis.class);

        // Create a call instance for looking up Retrofit contributors.
        Log.v("tag",params[0]);
        Call<GeocodeResponse> call = googleApis.site(params[0]);

        // Fetch and print a list of the contributors to the library.
        GeocodeResponse places = null;
        try {
            places = call.clone().execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }

           Log.v("tag",places.getResults().get(0).getFormatted_address() + " (----------------------------------+)" );

        return places;
    }
}

