package com.br.getmovies.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class FavoritesDAO {

    private SQLiteDatabase db;
    private DbHelper banco;

    public FavoritesDAO(Context context){
        banco = new DbHelper(context);
    }

    public String save( Movie movie ){

        ContentValues valores;
        long resultado;
        db = banco.getWritableDatabase();

        try{
            valores = new ContentValues();
            valores.put( banco.ID_MOVIE, movie.getId() );
            valores.put( banco.TITLE, movie.getTitle() );
            valores.put( banco.POSTER, movie.getPoster_path() );
            valores.put( banco.OVERVIEW, movie.getOverview() );
            valores.put( banco.VOTE_AVERAGE, movie.getVote_average() );
            valores.put( banco.RELEASE_DATE, movie.getRelease_date() );
            resultado = db.insert( banco.TABELA,null, valores );
            System.out.printf("Resultado Insert: " + resultado);
            db.close();
            if ( resultado != -1 ) {
                return movie.getTitle() + " adicionado aos favoritos!";
            }
        } catch ( SQLException e ) {
            Log.e( "ERRO - ", e.getMessage() );
        }
        return "ERRO - " + movie.getTitle() + " já adicionado aos favoritos!!";
    }

    public ArrayList<Movie> list() {
        ArrayList<Movie> lista = new ArrayList<>();
        Cursor cursor;
        String[] campos = { DbHelper.ID_MOVIE, DbHelper.TITLE, DbHelper.POSTER, DbHelper.OVERVIEW, DbHelper.VOTE_AVERAGE, DbHelper.RELEASE_DATE};
        db = banco.getReadableDatabase();
        cursor = db.query( DbHelper.TABELA, campos,null,null,null,null,null );
        if ( cursor.moveToFirst()  ) {
            do {
                int id = cursor.getInt(0) ;
                String idMovie = cursor.getString(0) ;
                String title = cursor.getString(1) ;
                String poster_path = cursor.getString(2) ;
                String overview = cursor.getString(3) ;
                String vote_average = cursor.getString(4) ;
                String release_date = cursor.getString(5);
                Movie movie = new Movie( idMovie, title, poster_path, overview, vote_average, release_date );
                lista.add( movie );
            } while ( cursor.moveToNext());
            return lista;
        }
        return null;
    }

    public String deletar( Movie m ) {
        String where = DbHelper.ID_MOVIE + "= " + m.getId();
        db = banco.getReadableDatabase();
        db.delete( DbHelper.TABELA, where, null );
        db.close();
        return "Filme Removido dos favoritos!";
    }

}
