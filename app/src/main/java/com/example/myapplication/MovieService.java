package com.example.myapplication;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieService {

    @GET("movie/changes?api_key=" + Constants.API_KEY_V3_AUTH)
    Call<MovieChange> getChangedMovies();

    @GET("movie/changes?api_key=" + Constants.API_KEY_V3_AUTH)
    Call<List<Movie>> getChangedMoviesByDateAndPage(@Query("endDate") String endDate, @Query("startDate") String startDate, @Query("page") int page);

    @GET("movie/{id}?api_key=" + Constants.API_KEY_V3_AUTH)
    Call<Movie> getMovie(@Path("id") int id);

    @GET("movie/{id}/images?api_key=" + Constants.API_KEY_V3_AUTH)
    Call<Images> getImages(@Path("id") int id);

}
