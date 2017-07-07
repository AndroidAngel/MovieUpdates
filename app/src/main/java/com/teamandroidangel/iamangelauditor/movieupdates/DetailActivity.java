package com.teamandroidangel.iamangelauditor.movieupdates;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import static android.R.attr.name;

/**
 * Created by iamangelauditor on 03/07/2017.
 */

public class DetailActivity extends AppCompatActivity {

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

        nameTitle = (TextView) findViewById(R.id.title);
        movieImageDetail = (ImageView) findViewById(R.id.movie_image_detail);


        item = (MovieData) getIntent().getSerializableExtra("item");
        itemId = getIntent().getLongExtra("itemId", 0);
        itemIndex = getIntent().getIntExtra("itemIndex", 0);
        code = getIntent().getIntExtra("code", 0);

        nameTitle.setText(item.getMovie_name());
        Picasso.with(this).load(item.getMovie_url()).into(movieImageDetail);




    }
}