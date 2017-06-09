package com.teamandroidangel.iamangelauditor.movieupdates;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.support.v7.widget.RecyclerView;


/**
 * Created by iamangelauditor on 07/06/2017.
 */

public class ImageAdapter extends BaseAdapter {

    private Context mContext;
    private final int[] Imageid;

    public ImageAdapter(Context c, int[] Imageid) {

        mContext = c;
        this.Imageid = Imageid;
    }
    @Override
    public int getCount() {
        return Imageid.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)
                mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View gridView;
        if (convertView == null) {
            gridView = inflater.inflate(R.layout.grid_layout, null);
            ImageView imageView = (ImageView)
                    gridView.findViewById(R.id.grid_item_image_movie);
            imageView.setImageResource(Imageid[position]);
        } else {
            gridView = (View) convertView;
        }
        return gridView;
        }
}

