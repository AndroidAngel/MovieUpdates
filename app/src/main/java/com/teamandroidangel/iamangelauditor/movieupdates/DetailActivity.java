package com.teamandroidangel.iamangelauditor.movieupdates;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by iamangelauditor on 03/07/2017.
 */

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);

        String title = getIntent().getStringExtra("title");

        TextView titleTextView = (TextView) findViewById(R.id.title);
        titleTextView.setText(title);
    }
}