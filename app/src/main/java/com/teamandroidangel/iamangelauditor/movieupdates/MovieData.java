package com.teamandroidangel.iamangelauditor.movieupdates;

/**
 * Created by iamangelauditor on 13/06/2017.
 */

public class MovieData {

    private String movie_name;

    private String movie_image_url;

    public String getMovie_name(){
        return movie_name;
    }

    public String getMovie_image_url() {
        return movie_image_url;
    }

    public void setMovie_image_url(String movie_image_url) {
        this.movie_image_url = movie_image_url;
    }

    public void setMovie_name(String movie_name) {
        this.movie_name = movie_name;
    }


}