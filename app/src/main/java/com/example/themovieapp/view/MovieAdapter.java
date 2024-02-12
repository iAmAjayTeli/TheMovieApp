package com.example.themovieapp.view;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.themovieapp.DetailActivity;
import com.example.themovieapp.databinding.ActivityDetailBinding;
import com.example.themovieapp.databinding.MovieListSampleBinding;
import com.example.themovieapp.model.Movies;
import com.example.themovieapp.R;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.viewHolder> {
    ArrayList<Movies> moviesList;
    Context context;


    public MovieAdapter(ArrayList<Movies> moviesList, Context context) {
        this.moviesList = moviesList;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      MovieListSampleBinding binding= DataBindingUtil.inflate(
              LayoutInflater.from(parent.getContext()),
              R.layout.movie_list_sample,
              parent,
              false
      );

      return new viewHolder(binding);


    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
  Movies movies=moviesList.get(position);
  holder.movieListSampleBinding.setMovies(movies);

      holder.movieListSampleBinding.movieCardView.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent intent=new Intent(context, DetailActivity.class);
              intent.putExtra("movie_title", movies.getTitle());
//              Movies movies1= moviesList.get(position);
//              holder.Detailbinding.setMovie(movies1);
//              Toast.makeText(context, ""+position , Toast.LENGTH_SHORT).show();
              context.startActivity(intent);
          }
      });
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
      MovieListSampleBinding movieListSampleBinding;
        ActivityDetailBinding Detailbinding;

        public viewHolder(@NonNull MovieListSampleBinding movieListSampleBinding) {
            super(movieListSampleBinding.getRoot());
            this.movieListSampleBinding=movieListSampleBinding;
        }
    }
}
