package com.teamandroidangel.iamangelauditor.movieupdates;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

import static com.teamandroidangel.iamangelauditor.movieupdates.R.id.list_movie;


public class MainActivity extends AppCompatActivity {

    public static final String LOG_TAG = MainActivity.class.getName();

    private final String API_KEY = "f05f845199266c1ce950bc8a260a0258";

    private final String MOVIES_ENDPOINT = "https://api.themoviedb.org/3/";

    private MovieDataAdapter mAdapter;

    private final String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/w185";

    private final int MAIN_ACTIVITY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {


        final GridView gridView = (GridView) findViewById(list_movie);
        mAdapter = new MovieDataAdapter(this, new ArrayList<MovieData>());

        gridView.setAdapter(mAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent movieIntent = new Intent(MainActivity.this, DetailActivity.class);
                MovieData selectedMovieData = (MovieData) parent.getItemAtPosition(position);

                movieIntent.putExtra("item", (Serializable) selectedMovieData);
                movieIntent.putExtra("itemIndex", position);
                movieIntent.putExtra("itemId", id);
                startActivityForResult(movieIntent, MAIN_ACTIVITY_REQUEST_CODE);

            }
        });

        // we will delegate preparing data on this utility class - also this will not be synchronous
        NetworkUtils.getMovieList(this.MOVIES_ENDPOINT, this.IMAGE_BASE_URL, new CallBackHandler() {
            @Override
            public void onComplete(ArrayList<MovieData> movies) {


                //somethign wrong with the imageadapter
                //ImageAdapter mAdapter = new ImageAdapter(getApplicationContext(),movies);
                //recyclerView.setAdapter(mAdapter);

                // this list contains all your movies
                ArrayList<MovieData> movs = movies;
                mAdapter.clear();
                if (movs != null && !movs.isEmpty()) {
                    mAdapter.addAll(movs);
                    Log.i(MainActivity.LOG_TAG, "movie retrieved");


                }


            }

            @Override
            public void onComplete(MovieData movieData) {

                MovieData movs = movieData;
            }

            @Override
            public void onFail(Throwable t) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemThatWasClickedId = item.getItemId();
        if (itemThatWasClickedId == R.id.action_refresh) {
            Context context = MainActivity.this;
            String textToShow = "Refreshing";
            Toast.makeText(context, textToShow, Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}







