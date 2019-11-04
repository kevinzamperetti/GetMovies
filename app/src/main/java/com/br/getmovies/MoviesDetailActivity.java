package com.br.getmovies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
        Movie movie = getIntent().getParcelableExtra("movie");
        mTvTitle.setText(movie.getTitle());
        mTvVote.setText(movie.getVote_average());
        mTvLacamento.setText(movie.getRelease_date());
        mTvOver.setText(movie.getOverview());

        Picasso.get()
                .load(BASE_IMG + movie.getPoster_path())
                .into(mIvPoster);

    }

}
