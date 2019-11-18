package com.br.getmovies;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.br.getmovies.Adapter.FavoritesAdapter;
import com.br.getmovies.Adapter.MoviesAdapter;
import com.br.getmovies.Data.FavoritesDAO;
import com.br.getmovies.Data.Movie;

import java.util.ArrayList;

public class ListFavoritesMoviesActivity extends AppCompatActivity {

    public FavoritesAdapter adapter;
    ArrayList<Movie> listaFavorites;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getSupportActionBar().hide(); //esconde actionBar com nome do projeto

        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_list_favorites_movies );

        listaFavorites = new ArrayList<>();

        final FavoritesDAO favoritesDAO = new FavoritesDAO( getApplicationContext() );
        listaFavorites = favoritesDAO.list();

        adapter = new FavoritesAdapter(this, listaFavorites);
        ListView list = findViewById( R.id.listaFavorites );
        list.setAdapter( adapter );


        list.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Movie m = ( Movie) parent.getItemAtPosition( position );

                context = view.getContext();
                Intent intent = new Intent(view.getContext(), MoviesDetailActivity.class);
                Movie movie = listaFavorites.get(position);
                intent.putExtra("movie",movie);
                context.startActivity(intent);
                //
//                Intent intent = new Intent(getApplicationContext(), EditarActivity.class);
//
//                // Identificador do dado a ser passado,
//                intent.putExtra( "id", m.getId() );
//
//                // Dados a serem passados
//                intent.putExtra( "title", m.getTitle() );
//                intent.putExtra( "movie", m );
//                startActivity( intent );

            }
        } );
    }
}
