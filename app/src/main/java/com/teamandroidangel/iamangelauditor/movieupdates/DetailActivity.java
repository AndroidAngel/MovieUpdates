package com.teamandroidangel.iamangelauditor.movieupdates;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by iamangelauditor on 03/07/2017.
 */

public class DetailActivity extends AppCompatActivity {

    TextView runTime;
    TextView releaseDate;
    TextView nameTitle;
    ImageView movieImageDetail;
    long itemId;
    int itemIndex;
    MovieData item;
    int code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);

        runTime = (TextView)findViewById(R.id.runtimeId);
        releaseDate = (TextView)findViewById(R.id.yearId);
        nameTitle = (TextView) findViewById(R.id.titlemovie);
        movieImageDetail = (ImageView) findViewById(R.id.imagedetail);


        item = (MovieData) getIntent().getSerializableExtra("item");
        itemId = getIntent().getLongExtra("itemId", 0);
        itemIndex = getIntent().getIntExtra("itemIndex", 0);
        code = getIntent().getIntExtra("code", 0);

//        runTime.setText(item.getRunTime("runtime"));

 //       releaseDate.setText(item.getReleaseDate("release_date"));
        nameTitle.setText(item.getMovie_name());
        Picasso.with(this).load(item.getMovie_image_url()).into(movieImageDetail);


        Date dateObject = new Date();
        String formattedDate = formatDate(dateObject);
        String formattedTime = formatTime(dateObject);
        releaseDate.setText(formattedDate);
        runTime.setText(formattedTime);
    }
    private String formatDate(Date dateObject){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
        return dateFormat.format(dateObject);
    }

    private String formatTime(Date dateObject){
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }
}
