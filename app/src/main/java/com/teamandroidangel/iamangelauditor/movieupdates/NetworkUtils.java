package com.teamandroidangel.iamangelauditor.movieupdates;

import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.util.ArrayList;

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

    public static void getMovieDetail(final String baseURL, final String imageBaseURL,final int movieId, final CallBackHandler handler){
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
                handler.onComplete(convertJsonToMovieDetail(resp, imageBaseURL,baseURL));

            }
            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                handler.onFail(t);
            }
        });
    }


    public static void getMovieList(final String baseURL,final String imageBaseURL, final CallBackHandler handler) {

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
                handler.onComplete(convertJsonToMovieList(resp, imageBaseURL, baseURL));

            }
            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                handler.onFail(t);
            }
        });
    }
    private static MovieData convertJsonToMovieDetail(JsonObject movie, String imageBaseURL, String baseURL){

        MovieData mov = new MovieData();

        mov.setRunTime(movie.get("runtime").getAsInt());
        mov.setMovie_name(movie.get("title").getAsString());
        mov.setReleaseDate(movie.get("release_date").getAsString());

        // the poster image url given is only partial and does not contain the base url, so we will combine them
        int runTime = movie.get("runtime").getAsInt();
        String releaseDate = movie.get("release_date").getAsString();
        String posterPath = movie.get("poster_path").getAsString();
        String movieImageUrl = imageBaseURL+posterPath;
        String movieUrl = baseURL + "movie/" + movie.get("id").getAsString()+ "?api_key=f05f845199266c1ce950bc8a260a0258";
        mov.setMovie_url(movieUrl);
        mov.setMovie_image_url(movieImageUrl);
        mov.getReleaseDate(releaseDate);
        mov.getRunTime(runTime);

        return mov;

    }
    private static ArrayList<MovieData> convertJsonToMovieList(JsonArray movies, String imageBaseURL, String baseURL) {

        ArrayList<MovieData> movs = new ArrayList<>();

        try {

            for (JsonElement pa : movies) {

                MovieData mov = new MovieData();

                JsonObject props = pa.getAsJsonObject();


                //to get a property of the json use props.get("name_of_property").getAsString or getAs whatever data type like getAsInt
                mov.setRunTime(props.get("runtime").getAsInt());
                mov.setMovie_name(props.get("title").getAsString());
                mov.setReleaseDate(props.get("release_date").getAsString());

                // the poster image url given is only partial and does not contain the base url, so we will combine them
                int runTime = props.get("runtime").getAsInt();
                String releaseDate = props.get("release_date").getAsString();
                String posterPath = props.get("poster_path").getAsString();
                String movieImageUrl = imageBaseURL+posterPath;
                String movieUrl = baseURL + "movie/" + props.get("id").getAsString()+ "?api_key=f05f845199266c1ce950bc8a260a0258";
                mov.setMovie_url(movieUrl);
                mov.setMovie_image_url(movieImageUrl);
                mov.getReleaseDate(releaseDate);
                mov.setRunTime(runTime);


                movs.add(mov);
            }
        } catch (JsonParseException e) {
            e.printStackTrace();

            Log.e("QueryUtils", "Problem parsing the movie JSON results", e);
        }
        return movs;

    }
}