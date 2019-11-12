package com.br.getmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.br.getmovies.Data.Movie;
import com.squareup.picasso.Picasso;

public class MoviesDetailActivity extends AppCompatActivity {


    private final String BASE_IMG = "https://image.tmdb.org/t/p/w500/";
    TextView mTvTitle;
    TextView mTvVote;
    TextView mTvOver;
    TextView mTvLacamento;
    ImageView mIvPoster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_detail);
        mIvPoster = findViewById(R.id.iv_poster);
        mTvLacamento = findViewById(R.id.tv_lacamento);
        mTvVote = findViewById(R.id.tv_voting);
        mTvOver = findViewById(R.id.tv_overview);
        mTvTitle = findViewById(R.id.tv_title);
        final Movie movie = getIntent().getParcelableExtra("movie");
        mTvTitle.setText(movie.getTitle());
        mTvVote.setText(movie.getVote_average());
        mTvLacamento.setText(movie.getRelease_date());
        mTvOver.setText(movie.getOverview());
        Button btnSimilares = findViewById(R.id.btnSimilares);

        Picasso.get()
                .load(BASE_IMG + movie.getPoster_path())
                .into(mIvPoster);

        //btnSimilares.onClick
        btnSimilares.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                Intent it = new Intent( getApplicationContext(), ListSimilarMoviesActivity.class );
                it.putExtra("idMovie", movie.getId());

                startActivity( it );
            }
        });

    }

}
