package com.teamandroidangel.iamangelauditor.movieupdates;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by iamangelauditor on 30/06/2017.
 */

public class MovieDataAdapter extends ArrayAdapter<MovieData> {

    public MovieDataAdapter(Context context,List<MovieData> movs) {
        super(context, 0, movs);
    }

    @Override
    public View getView(int position, View convertView,
                                 ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.movie_list_item, parent, false);
        }

        MovieData currentMovieData = getItem(position);
        ImageView imageView = (ImageView) listItemView.findViewById(R.id.movie_image);
        Picasso.get().load(currentMovieData.getMovie_image_url()).into(imageView);
        return listItemView;
    }
}
