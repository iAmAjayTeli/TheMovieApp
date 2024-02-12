package com.example.themovieapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ImageView;

import com.example.themovieapp.MyViewModel.MovieViewModel;
import com.example.themovieapp.databinding.ActivityDetailBinding;
import com.example.themovieapp.model.Movies;
import com.example.themovieapp.view.MovieAdapter;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {
   ActivityDetailBinding binding;
   ImageView image;
   Movies movies;
   MovieViewModel viewModel;
   MovieAdapter adapter;
    private List<Movies> movieList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        viewModel = new ViewModelProvider(this).get(MovieViewModel.class);
        movieList = viewModel.getAllMovie().getValue();

        adapter = new MovieAdapter((ArrayList<Movies>) movieList, this);
        adapter.notifyDataSetChanged();

        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail);

        // Retrieve movie title passed from the previous activity
        String movieTitle = getIntent().getStringExtra("movie_title");
    }
}