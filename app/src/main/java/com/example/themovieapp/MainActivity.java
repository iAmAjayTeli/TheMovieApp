package com.example.themovieapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;

import com.example.themovieapp.model.Movies;
import com.example.themovieapp.MyViewModel.MovieViewModel;
import com.example.themovieapp.databinding.ActivityMainBinding;
import com.example.themovieapp.view.MovieAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Movies> movies;
    private MovieAdapter adapter;
    private MovieViewModel viewModel;
    private RecyclerView recyclerView;
    private ActivityMainBinding binding;
    private SwipeRefreshLayout swipeRefreshLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding= DataBindingUtil.setContentView(this, R.layout.activity_main);

        movies=new ArrayList<>();
        recyclerView=binding.recyclerview;
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setHasFixedSize(true);

        viewModel=new ViewModelProvider(this).get(MovieViewModel.class);

        getPopularMovies();

        swipeRefreshLayout=binding.swipeRefreshLayout;
        swipeRefreshLayout.setColorSchemeResources(R.color.black);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPopularMovies();
            }
        });


    }

    private void getPopularMovies() {
        viewModel.getAllMovie().observe(this, new Observer<List<Movies>>() {
            @Override
            public void onChanged(List<Movies> moviesFromLiveData) {
                movies=(ArrayList<Movies>) moviesFromLiveData;
                displayMoviesInRecyclerView();
            }
        });
    }

    private void displayMoviesInRecyclerView() {
        adapter=new MovieAdapter(movies, this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}