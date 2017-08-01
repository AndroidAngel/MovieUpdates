package com.teamandroidangel.iamangelauditor.movieupdates;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.teamandroidangel.iamangelauditor.movieupdates.databinding.DetailActivityBinding;
import com.teamandroidangel.iamangelauditor.movieupdates.NetworkUtils;



import java.text.SimpleDateFormat;
import java.util.Date;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

/**
 * Created by iamangelauditor on 03/07/2017.
 */

public class DetailActivity extends AppCompatActivity {

    TextView runTime;
    TextView voteAverage;
    TextView overview;
    TextView releaseDate;
    TextView nameTitle;
    ImageView movieImageDetail;
    long itemId;
    int itemIndex;
    MovieData item;
    int code;

    // DetailActivityBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);

     //   mBinding = DataBindingUtil.setContentView(this, R.layout.detail_activity);
       // MovieData movieDataInfo = NetworkUtils.movs();
        //displayMovieDataInfo(movieDataInfo);


//    private void displayMovieDataInfo(MovieData info){
//
//        mBinding.titleMovie.setText(info.movie_name);
//        mBinding.yearId.setText(info.releaseDate);
//        mBinding.runtimeId.setText(info.runTime);
//        mBinding.voteAverageId.setText(info.voteAverage.toString());
//        mBinding.overviewId.setText(info.overview);

        voteAverage = (TextView)findViewById(R.id.voteAverageId);
        overview = (TextView)findViewById(R.id.overviewId);
        runTime = (TextView)findViewById(R.id.runtimeId);
        releaseDate = (TextView)findViewById(R.id.yearId);
        nameTitle = (TextView) findViewById(R.id.titleMovie);
        movieImageDetail = (ImageView) findViewById(R.id.imageDetail);


        item = (MovieData) getIntent().getSerializableExtra("item");
        itemId = getIntent().getLongExtra("itemId", 0);
        itemIndex = getIntent().getIntExtra("itemIndex", 0);
        code = getIntent().getIntExtra("code", 0);
        runTime.setText(Integer.toString(item.getRunTime()));
        overview.setText(item.getOverview());
        voteAverage.setText(Long.toString(item.getVoteAverage()));

        releaseDate.setText(item.getReleaseDate());


        nameTitle.setText(item.getMovie_name());
        Picasso.with(this).load(item.getMovie_image_url()).into(movieImageDetail);

        String releaseDateString = item.getReleaseDate();
        Date releaseDateObject = new Date();
        try {
            releaseDateObject = new SimpleDateFormat("yyyy-MM-dd").parse(releaseDateString);
        }catch(java.text.ParseException e){
            e.printStackTrace();
        }
        String formattedDate = formatDate(releaseDateObject);

        releaseDate.setText(formattedDate);

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