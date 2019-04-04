package com.example.myapplication;

import android.util.Log;

import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieRepository {
    private static MovieRepository instance;
    private Retrofit retrofit;
    private MovieService mSrv;

    private MovieRepository() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()))
                .build();
        mSrv = retrofit.create(MovieService.class);
    }

    public static synchronized MovieRepository newInstance() {
        if(instance == null) instance = new MovieRepository();
        return instance;
    }

    public LiveData<List<Movie>> getMovies() {
        final MutableLiveData<List<Movie>> data = new MutableLiveData<>();
        mSrv.getChangedMovies().enqueue(new Callback<MovieChange>() {
            @Override
            public void onResponse(Call<MovieChange> call, Response<MovieChange> response) {
                if(response.isSuccessful()) {
                    List<Movie> movies = new ArrayList<>();
                    MovieChange movieChange = response.body();
                    for(Movie mc : movieChange.getMovies()) {
                        if (!mc.isAdult()) {
                            mSrv.getMovie(mc.getId()).enqueue(new Callback<Movie>() {
                                @Override
                                public void onResponse(Call<Movie> call, Response<Movie> response) {
                                    Movie m = response.body();
                                    mSrv.getImages(mc.getId()).enqueue(new Callback<Images>() {
                                        @Override
                                        public void onResponse(Call<Images> call, Response<Images> response) {
                                            Images imgs = response.body();
                                            m.setImages(imgs);
                                            for(Poster p : imgs.getPosters()) {

                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<Images> call, Throwable t) {
                                            Log.d(this.getClass().getName(), "failed to get images" + t.getMessage());
                                        }
                                    });
                                    movies.add(m);
                                }

                                @Override
                                public void onFailure(Call<Movie> call, Throwable t) {
                                    Log.d(this.getClass().getName(), "failed to get movie" + t.getMessage());
                                }
                            });
                        }
                    }
                    data.setValue(movies);
                }
            }

            @Override
            public void onFailure(Call<MovieChange> call, Throwable t) {
                Log.d(this.getClass().getName(), "failed to get movie changes" + t.getMessage());
            }
        });
        return data;
    }

}
