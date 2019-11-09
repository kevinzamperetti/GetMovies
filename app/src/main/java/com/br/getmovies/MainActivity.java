package com.br.getmovies;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.br.getmovies.Adapter.MoviesAdapter;
import com.br.getmovies.Data.MoviesDTO;

public class MainActivity extends AppCompatActivity {


    //private static final String API_KEY = "d15439ba9445688264047fbb91fce4c4";
    private static final String API_KEY = BuildConfig.API_KEY;
    private MoviesDTO mList;
    private CoordinatorLayout coordinatorLayout;
    private RecyclerView mRecyclerView;
    private MoviesAdapter mMovieAdapter;
    Context context;
    private TextView mErrorMessageDisplay;
    private ProgressBar mLoadingIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnPopular = findViewById(R.id.btnPopular);
        Button btnTopRated = findViewById(R.id.btnTopRated);
        Button btnSpecific = findViewById(R.id.btnSpecific);
        Button btnSimilar = findViewById(R.id.btnSimilar);

        mMovieAdapter = new MoviesAdapter();

        if (!VerificaConexao(this)) {
            showErrorMessage(getString(R.string.error_internet));
        }

        //btnPopular.onClick
        btnPopular.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                Intent it = new Intent( MainActivity.this, ListPopularMoviesActivity.class );
                startActivity( it );
            }
        });

        //btnSpecific.onClick
        btnSpecific.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
//                Intent intent = new Intent(v.getContext(), MoviesDetailActivity.class);
//                Movie movie = mMovieAdapter.mMoviesData.get(19404);
//                intent.putExtra("movie",movie);
//                context.startActivity(intent);
            }
        });

        //btnTopRated.onClick
        btnTopRated.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                Intent it = new Intent( MainActivity.this, ListTopRatedMoviesActivity.class );
                startActivity( it );
            }
        });

        //btnSimilar.onClick
        btnSimilar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                Intent it = new Intent( MainActivity.this, ListSimilarMoviesActivity.class );
                startActivity( it );
            }
        });

    }

    private void showErrorMessage(String msg) {
        mRecyclerView.setVisibility(View.INVISIBLE);
        mErrorMessageDisplay.setVisibility(View.VISIBLE);
        Snackbar snackbarError = Snackbar.make(coordinatorLayout, msg, Snackbar.LENGTH_LONG);
        snackbarError.show();
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
}
