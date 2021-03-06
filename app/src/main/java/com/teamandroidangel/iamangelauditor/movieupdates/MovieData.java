package com.teamandroidangel.iamangelauditor.movieupdates;


import android.widget.TextView;

import java.io.Serializable;

/**
 * Created by iamangelauditor on 13/06/2017.
 */

public class MovieData extends Object implements Serializable {


    public int runTime;

    public Long voteAverage;

    public String overview;

    public String movie_url;

    public String releaseDate;

    public String movie_name;

    public String movie_image_url;

    public Long getVoteAverage(){
        return this.voteAverage;
    }

    public String getOverview(){
        return this.overview;
    }

    public int getRunTime(){
        return this.runTime;
    }

    public String getReleaseDate(){
        return this.releaseDate;
    }

    public String getMovie_url() {
        return movie_url;
    }

    public String getMovie_name() {
        return movie_name;
    }

    public String getMovie_image_url() {
        return movie_image_url;
    }

    public void setVoteAverage(Long voteAverage){
        this.voteAverage = voteAverage;
    }

    public void setRunTime(int runTime){
        this.runTime = runTime;
    }

    public void setOverview(String overview){
        this.overview = overview;
    }

    public void setReleaseDate(String releaseDate){
        this.releaseDate = releaseDate;
    }

    public void setMovie_image_url(String movie_image_url) {
        this.movie_image_url = movie_image_url;
    }

    public void setMovie_name(String movie_name) {
        this.movie_name = movie_name;
    }

    public void setMovie_url(String movie_url) {
        this.movie_url = movie_url;
    }

}