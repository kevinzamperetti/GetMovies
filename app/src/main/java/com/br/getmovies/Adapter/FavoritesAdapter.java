package com.br.getmovies.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.br.getmovies.Data.Movie;
import com.br.getmovies.R;

import java.util.List;

public class FavoritesAdapter extends ArrayAdapter {

    public FavoritesAdapter(Context context, List<Movie> objetcts) {
        super(context, 0, objetcts);
    }

    public View getView(int position, View convertView, ViewGroup parent){
        View listItemView =convertView;

        if(listItemView==null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_favorites, parent, false);
        }

        Movie current = (Movie) getItem(position);
        TextView title =  (TextView) listItemView.findViewById(R.id.txtTitle);
        title.setText( current.getTitle() );
        return  listItemView;
    }
}
