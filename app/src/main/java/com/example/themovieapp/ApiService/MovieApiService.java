package com.example.themovieapp.ApiService;

import com.example.themovieapp.model.Movies;
import com.example.themovieapp.model.Results;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieApiService {


    @GET("movie/popular")
    Call<Results> getPopularMovie(@Query("api_key") String apiKey);

    @GET("movie/{movie_id}")
    Call<Results> getMovieDetails(
            @Path("movie_id") int movieId,
            @Query("api_key") String apiKey
    );
}
