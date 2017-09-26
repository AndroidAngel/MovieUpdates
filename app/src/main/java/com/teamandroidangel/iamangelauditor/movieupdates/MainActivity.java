package com.teamandroidangel.iamangelauditor.movieupdates;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
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

    private final String API_KEY = "000";

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

                movieIntent.putExtra("item", selectedMovieData);
                movieIntent.putExtra("itemIndex", position);
                movieIntent.putExtra("itemId", id);
                startActivityForResult(movieIntent, MAIN_ACTIVITY_REQUEST_CODE);

            }
        });

        NetworkUtils.getMovieList(this.MOVIES_ENDPOINT, this.IMAGE_BASE_URL, new CallBackHandler() {
            @Override
            public void onComplete(ArrayList<MovieData> movies) {


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
        MenuInflater inflater =getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.popular_movie:
                return true;
            case R.id.top_rated:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}








