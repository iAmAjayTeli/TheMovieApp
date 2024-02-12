package com.example.themovieapp.model;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.example.themovieapp.ApiService.MovieApiService;
import com.example.themovieapp.ApiService.RetrofitInstance;
import com.example.themovieapp.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {

    private ArrayList<Movies> moviesList=new ArrayList<>();

    private MutableLiveData<List<Movies>>  mutableLiveData;
    private MutableLiveData<List<Movies>> movieDetailLiveData;

    private Application application;

    public MovieRepository(Application application) {
        this.application = application;
    }

    public MutableLiveData<List<Movies>> getMutableLiveData() {
        if (mutableLiveData == null) {
            mutableLiveData = new MutableLiveData<>();
        }

        MovieApiService movieApiService= RetrofitInstance.getService();
        Call<Results> call= movieApiService.getPopularMovie(application.getApplicationContext().getString(R.string.api_key));

        call.enqueue(new Callback<Results>() {
            @Override
            public void onResponse(Call<Results> call, Response<Results> response) {
                Results results=response.body();
                if(results!=null && results.getResults()!=null){
                    moviesList= (ArrayList<Movies>) results.getResults();
                    mutableLiveData.setValue(moviesList);
                }
            }

            @Override
            public void onFailure(Call<Results> call, Throwable t) {

            }
        });

        return mutableLiveData;
    }

}
