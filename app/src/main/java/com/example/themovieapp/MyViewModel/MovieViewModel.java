package com.example.themovieapp.MyViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.themovieapp.model.MovieRepository;
import com.example.themovieapp.model.Movies;

import java.util.List;

public class MovieViewModel extends AndroidViewModel {

    private MovieRepository repository;

    public MovieViewModel(@NonNull Application application) {
        super(application);
        this.repository = new MovieRepository(application);
    }

  public MutableLiveData<List<Movies>> getAllMovie(){
       return repository.getMutableLiveData();
  }

  //Method to fetch the details of the movie

}
