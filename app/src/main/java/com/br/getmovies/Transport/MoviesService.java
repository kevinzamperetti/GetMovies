package com.br.getmovies.Transport;

import com.br.getmovies.Data.MoviesDTO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MoviesService {


    @GET("popular")
    Call<MoviesDTO> getApiPopularMovies(@Query("api_key") String apiKey);

    @GET("top_rated")
    Call<MoviesDTO> getApiTopRatedMovies(@Query("api_key")String apiKey);

    @GET("/{id}")
    Call<MoviesDTO> getMovieDetails(@Path("id") Integer id, @Query("api_key")String apiKey);

    @GET("/{id}/similar")
    Call<MoviesDTO> getMovieSimilar(@Path("id") Integer id, @Query("api_key")String apiKey);
}
