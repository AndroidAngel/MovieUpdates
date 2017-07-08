package com.teamandroidangel.iamangelauditor.movieupdates;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;

import static android.R.attr.name;

/**
 * Created by iamangelauditor on 03/07/2017.
 */

public class DetailActivity extends AppCompatActivity {

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

        releaseDate = (TextView)findViewById(R.id.yearId);
        nameTitle = (TextView) findViewById(R.id.titlemovie);
        movieImageDetail = (ImageView) findViewById(R.id.imagedetail);


        item = (MovieData) getIntent().getSerializableExtra("item");
        itemId = getIntent().getLongExtra("itemId", 0);
        itemIndex = getIntent().getIntExtra("itemIndex", 0);
        code = getIntent().getIntExtra("code", 0);

        releaseDate.setText(item.getReleaseDate("release_date"));
        nameTitle.setText(item.getMovie_name());
        Picasso.with(this).load(item.getMovie_image_url()).into(movieImageDetail);


      
        Date dateObject = new Date();
        String formattedDate = formatDate(dateObject);
        releaseDate.setText(formattedDate);
    }
    private String formatDate(Date dateObject){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
        return dateFormat.format(dateObject);
    }
}
