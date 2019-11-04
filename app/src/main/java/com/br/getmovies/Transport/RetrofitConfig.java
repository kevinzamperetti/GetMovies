package com.br.getmovies.Transport;


import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitConfig {

    private final Retrofit retrofit;

    private  final String BASE_URL="https://api.themoviedb.org/3/movie/";
    private  final String BASE_IMG="https://image.tmdb.org/t/p/w500/";



    public RetrofitConfig(){
     this.retrofit =  new Retrofit.Builder()
             .baseUrl(BASE_URL)
             .addConverterFactory(JacksonConverterFactory.create())
             .build();

    }

    public MoviesService get(){
        return this.retrofit.create(MoviesService.class);
    }

}
