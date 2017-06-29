package com.teamandroidangel.iamangelauditor.movieupdates;

import java.util.ArrayList;

/**
 * Created by iamangelauditor on 14/06/2017.
 */

// a custom callback handler that we will be using for data transformation, etc
public interface  CallBackHandler {
    void onComplete(ArrayList<MovieData> movies);
    void onFail(Throwable t);

    void onComplete(MovieData movieData);
}

