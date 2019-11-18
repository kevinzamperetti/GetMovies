package com.br.getmovies.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

    public static final String NOME_BANCO ="GetMovies.db";
    public static final String TABELA ="Favorites";
    public static final String ID_MOVIE ="idMovie";
    public static final String TITLE ="title";
    public static final String POSTER = "poster_path";
    public static final String OVERVIEW = "overview";
    public static final String VOTE_AVERAGE = "vote_average";
    public static final String RELEASE_DATE = "release_date";


    public static final int VERSAO =1;

    public DbHelper( Context context ){
        super( context,NOME_BANCO,null, VERSAO );
    }

    @Override
    public void onCreate( SQLiteDatabase db ) {
        String sqlVersao1 = "CREATE TABLE " + TABELA + " (" +
                ID_MOVIE + " TEXT PRIMARY KEY," +
                POSTER + " TEXT," +
                OVERVIEW + " TEXT," +
                TITLE + " TEXT NOT NULL," +
                VOTE_AVERAGE + " TEXT," +
                RELEASE_DATE + " TEXT)";

        db.execSQL( sqlVersao1 );
    }

    @Override
    public void onUpgrade( SQLiteDatabase db, int oldVersion, int newVersion ) {
        db.execSQL( "Drop table if exists "+ TABELA );
        onCreate( db );
    }

}
