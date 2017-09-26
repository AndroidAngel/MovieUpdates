package com.teamandroidangel.iamangelauditor.movieupdates;

import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import static android.R.attr.id;


/**
 * Created by iamangelauditor on 14/06/2017.
 */

public interface MovieDBOrgService {

    //get all popular movies, other APIs will follow the same procedure
    @GET("http://api.themoviedb.org/3/movie/popular?api_key=0")
    Call<JsonObject> listPopularMovies();

    @GET("movie/{id}?api_key=0")
    Call<JsonObject> getMovieDetail(@Path("id") String id);

    @GET("http://api.themoviedb.org/3/movie/top_rated?api_key=0")
    Call<JsonObject> listTopRatedMovies();


}



