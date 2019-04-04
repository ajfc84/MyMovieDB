package com.example.myapplication;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MovieViewModel extends ViewModel {
    private static MovieRepository repo;

    private LiveData<List<Movie>> movie;

    static {
        repo = MovieRepository.newInstance();
    }

    MovieViewModel() {
        movie = repo.getMovies();
    }

    public LiveData<List<Movie>> getMovies() {
        if (movie == null) movie = new MutableLiveData<>();
        return movie;
    }

}
