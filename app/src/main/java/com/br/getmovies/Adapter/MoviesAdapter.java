package com.br.getmovies.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.br.getmovies.Data.Movie;
import com.br.getmovies.MoviesDetailActivity;
import com.br.getmovies.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieAdapterViewHolder> {

    private  final String BASE_IMG="https://image.tmdb.org/t/p/w500/";

    public ArrayList<Movie> mMoviesData;
    Context context;
    @NonNull
    @Override
    public MovieAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
         context = viewGroup.getContext();
        int layoutIdForListItens;
        layoutIdForListItens = R.layout.movie_list_item_par;

        Log.e("COUNT",String.valueOf(i));
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean sholdAttach = false;
        View view = inflater.inflate(layoutIdForListItens,viewGroup,sholdAttach);
        return new MovieAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapterViewHolder movieAdapterViewHolder, int i) {
        Log.e("COUNT2",String.valueOf(i));
            String title = mMoviesData.get(i).title.toString();
            String rate = mMoviesData.get(i).vote_average.toString();
            movieAdapterViewHolder.mMovieTitle.setText(title);
            Picasso.get()
                    .load(BASE_IMG+mMoviesData.get(i).poster_path)
                    . into(movieAdapterViewHolder.mIvPoster);
    }

    @Override
    public int getItemCount() {
        if (mMoviesData == null) return 0;
       return mMoviesData.size();
    }

    public class MovieAdapterViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener {

       public final TextView mMovieTitle;
       public final ImageView mIvPoster;

        public MovieAdapterViewHolder(View view) {
            super(view);
            mMovieTitle =  view.findViewById(R.id.tv_movie_title);
            mIvPoster =  view.findViewById(R.id.iv_poster);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            Intent intent = new Intent(v.getContext(), MoviesDetailActivity.class);
            Movie movie = mMoviesData.get(getAdapterPosition());
            intent.putExtra("movie",movie);
            context.startActivity(intent);

        }
    }

      public void setMovieData(ArrayList<Movie> data){
        mMoviesData=data;
        notifyDataSetChanged();
      }
}
