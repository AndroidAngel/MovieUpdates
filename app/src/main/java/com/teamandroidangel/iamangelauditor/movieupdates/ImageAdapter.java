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

/**
 * Created by iamangelauditor on 07/06/2017.
 */

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {

    private ArrayList<MovieData> movie;
    private Context context;

    public ImageAdapter(Context context,ArrayList<MovieData> movie){
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
        private ImageView grid_image_movie;

        public ViewHolder(View view) {
            super(view);

            grid_image_movie = (ImageView) view.findViewById(R.id.grid_image_movie);
        }

    }}