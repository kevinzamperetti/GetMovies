package com.br.getmovies.Transport;

import com.br.getmovies.Data.MoviesDTO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MoviesService {


    @GET("popular")
    Call<MoviesDTO> retrievePopularMovies(@Query("api_key") String apiKey);

    @GET("top_rated")
    Call<MoviesDTO> retriveTopRatedMovies(@Query("api_key")String apiKey);
}
