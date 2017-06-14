package com.teamandroidangel.iamangelauditor.movieupdates;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static android.widget.ImageView.ScaleType.CENTER_CROP;

/**
 * Created by iamangelauditor on 07/06/2017.
 */

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {

    private ArrayList<MoviePreferences> movie;
    private Context context;

    public ImageAdapter(Context context,ArrayList<MoviePreferences> movie){
        this.context = context;
        this.movie = movie;
    }

    @Override
    public ImageAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.grid_layout, viewGroup, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ImageAdapter.ViewHolder viewHolder, int i) {
        viewHolder.title_movie.setText(movie.get(i).getMovie_name());
        Picasso.with(context)
                .load(movie.get(i).getMovie_image_url())
                .resize(50,50)
                .into(viewHolder.grid_image_movie);
    }

    @Override
    public int getItemCount() {
        return movie.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title_movie;
        private ImageView grid_image_movie;

        public ViewHolder(View view) {
            super(view);

            title_movie = (TextView) view.findViewById(R.id.title_movie);
            grid_image_movie = (ImageView) view.findViewById(R.id.grid_image_movie);
        }

    }}