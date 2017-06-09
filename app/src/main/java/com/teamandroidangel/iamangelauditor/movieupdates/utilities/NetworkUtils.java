package com.teamandroidangel.iamangelauditor.movieupdates.utilities;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import static android.content.ContentValues.TAG;

/**
 * Created by iamangelauditor on 08/06/2017.
 */

public final class NetworkUtils {


    private final static  String POPULAR_MOVIE_URL =
            "http://api.themoviedb.org/3/movie/popular?api_key=f05f845199266c1ce950bc8a260a0258";


    private static final String MOVIE_BASE_URL = POPULAR_MOVIE_URL;

    final static String PARAM_QUERY = "q";
    final static String PARAM_SORT = "sort";
    private static final String format = "json";
    private static final int numOfImages = 4;


    public static URL buildUrl(String movieQuery) {

        Uri builtUri = Uri.parse(MOVIE_BASE_URL).buildUpon()
                .appendQueryParameter(PARAM_QUERY, movieQuery)
                .appendQueryParameter(PARAM_SORT, format)
                .appendQueryParameter(PARAM_SORT, Integer.toString(numOfImages))
                .build();
        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Log.v(TAG, "Built URI " + url);

        return url;
    }
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}


