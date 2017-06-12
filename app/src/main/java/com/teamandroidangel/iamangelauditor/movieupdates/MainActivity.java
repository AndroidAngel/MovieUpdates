package com.teamandroidangel.iamangelauditor.movieupdates;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {




    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            initViews();
        }
    private void initViews(){
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.card_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),2);
        recyclerView.setLayoutManager(layoutManager);

        ArrayList<MoviePreferences> moviePreferences = prepareData();
        ImageAdapter adapter = new ImageAdapter(getApplicationContext(),moviePreferences);
        recyclerView.setAdapter(adapter);

    }
    private ArrayList<MoviePreferences> prepareData() {

        ArrayList<MoviePreferences> movie_Preferences = new ArrayList<>();
        for (int i = 0; i < movie_name.length; i++) {
            MoviePreferences moviePreferences = new MoviePreferences();
            moviePreferences.setMovie_name(movie_name[i]);
            moviePreferences.setMovie_image_url(movie_image_url[i]);
            movie_Preferences.add(moviePreferences);
        }
        return movie_Preferences;
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




