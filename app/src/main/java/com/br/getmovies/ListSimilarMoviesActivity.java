package com.br.getmovies;

import android.content.Context;
import android.net.ConnectivityManager;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.br.getmovies.Adapter.MoviesAdapter;
import com.br.getmovies.Data.Movie;
import com.br.getmovies.Data.MoviesDTO;
import com.br.getmovies.Transport.RetrofitConfig;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListSimilarMoviesActivity extends AppCompatActivity {

    //private static final String API_KEY = "d15439ba9445688264047fbb91fce4c4";
    private static final String API_KEY = BuildConfig.API_KEY;
    private MoviesDTO mList;
    private CoordinatorLayout coordinatorLayout;
    private RecyclerView mRecyclerView;
    private MoviesAdapter mMovieAdapter;
    private TextView mErrorMessageDisplay;
    private ProgressBar mLoadingIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_similar_movies);

        mRecyclerView = findViewById(R.id.recyclerview_movies);
        coordinatorLayout = findViewById(R.id.coordinatorLayout);
        mErrorMessageDisplay = findViewById(R.id.tv_error);
        GridLayoutManager layoutManager = new GridLayoutManager(this, numberOfColumns(), GridLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mMovieAdapter = new MoviesAdapter();
        mRecyclerView.setAdapter(mMovieAdapter);
        mLoadingIndicator = findViewById(R.id.pb_loading_indicator);

        if (VerificaConexao(this)) {
            getSimilarMovies();
        } else {
            showErrorMessage(getString(R.string.error_internet));
        }
    }

    private void showErrorMessage(String msg) {
        mRecyclerView.setVisibility(View.INVISIBLE);
        mErrorMessageDisplay.setVisibility(View.VISIBLE);
        Snackbar snackbarError = Snackbar.make(coordinatorLayout, msg, Snackbar.LENGTH_LONG);
        snackbarError.show();
    }

    private void getSimilarMovies() {
        showProgressBar();
        Call<MoviesDTO> call = new RetrofitConfig().get().getApiSimilarMovies(475557, API_KEY);
        call.enqueue(new Callback<MoviesDTO>() {
            @Override
            public void onResponse(Call<MoviesDTO> call, Response<MoviesDTO> response) {
                if (response.isSuccessful() == true) {
                    if (response.body() != null) {
                        mList = response.body();
                    }
                    mMovieAdapter.setMovieData((ArrayList<Movie>) mList.getMovies());
                    hideProgressBar();
                } else {
                    showErrorMessage(getString(R.string.error_indisponivel));
                }
            }

            @Override
            public void onFailure(Call<MoviesDTO> call, Throwable t) {
                Log.e("CALLBACK", t.getMessage());
                showErrorMessage(getString(R.string.error_request));
            }
        });
    }

    private int numberOfColumns() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int widthDivider = 400;
        int width = displayMetrics.widthPixels;
        int nColumns = width / widthDivider;
        if (nColumns < 2) return 2;
        return nColumns;
    }

    public boolean VerificaConexao(Context contexto) {
        boolean conectado = false;
        ConnectivityManager conmag;
        conmag = (ConnectivityManager) contexto.getSystemService(Context.CONNECTIVITY_SERVICE);
        conmag.getActiveNetworkInfo();
        //Verifica o WIFI
        if (conmag.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnected()) {
            conectado = true;
        }
        //Verifica o 3G
        else if (conmag.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnected()) {
            conectado = true;
        } else {
            conectado = false;
        }
        return conectado;
    }

    private void showProgressBar() {
        mLoadingIndicator.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        mLoadingIndicator.setVisibility(View.GONE);
    }


}
