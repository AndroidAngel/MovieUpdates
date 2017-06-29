package com.teamandroidangel.iamangelauditor.movieupdates;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by iamangelauditor on 14/06/2017.
 */

public class NetworkUtils {

    private static final String LOG_TAG = NetworkUtils.class.getSimpleName();


    private NetworkUtils() {
    }

    public static void getMovieDetail(final String baseURL, final int movieId, final CallBackHandler handler){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // let's instantiate a service from the interface defined MovieDBOrgService
        MovieDBOrgService service = retrofit.create(MovieDBOrgService.class);

        // call list Popular movies
        Call<JsonObject> call = service.getMovieDetail(new Integer(movieId).toString());

        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                //IMPORTANT THIS IS THE PART THAT DOES THE PARSING
                JsonObject resp = response.body().getAsJsonObject("results");
                handler.onComplete(convertJsonToMovieDetail(resp, baseURL));

            }
            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                handler.onFail(t);
            }
        });
    }


    public static void getMovieList(final String baseURL,final CallBackHandler handler) {

        // let's use retrofit for simplicity
        // a retrofit api call builder needs the base url
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // let's instantiate a service from the interface defined MovieDBOrgService
        MovieDBOrgService service = retrofit.create(MovieDBOrgService.class);

        // call list Popular movies
        Call<JsonObject> call = service.listPopularMovies();

        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
            //IMPORTANT THIS IS THE PART THAT DOES THE PARSING
                JsonArray resp = response.body().getAsJsonArray("results");
                handler.onComplete(convertJsonToMovieList(resp, baseURL));

            }
            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                handler.onFail(t);
            }
        });
    }
    private static MovieData convertJsonToMovieDetail(JsonObject movie, String baseURL){

        MovieData mov = new MovieData();

        mov.setMovie_name(movie.get("title").getAsString());

        // the poster image url given is only partial and does not contain the base url, so we will combine them
        String posterPath = movie.get("poster_path").getAsString();
        String movieImageUrl = baseURL+posterPath;

        mov.setMovie_image_url(movieImageUrl);

        return mov;

    }
    private static ArrayList<MovieData> convertJsonToMovieList(JsonArray movies, String baseURL) {

        ArrayList<MovieData> movs = new ArrayList<>();

        try {

            for (JsonElement pa : movies) {

                MovieData mov = new MovieData();

                JsonObject props = pa.getAsJsonObject();


                //to get a property of the json use props.get("name_of_property").getAsString or getAs whatever data type like getAsInt

                mov.setMovie_name(props.get("title").getAsString());

                // the poster image url given is only partial and does not contain the base url, so we will combine them
                String posterPath = props.get("poster_path").getAsString();
                String movieImageUrl = baseURL+posterPath;

                mov.setMovie_image_url(movieImageUrl);

                movs.add(mov);
            }
        } catch (JsonParseException e) {
            e.printStackTrace();

            Log.e("QueryUtils", "Problem parsing the movie JSON results", e);
        }
        return movs;

    }
}