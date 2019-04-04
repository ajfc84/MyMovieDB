package com.example.myapplication;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private List<Movie> mMovies;

    static class MovieViewHolder extends RecyclerView.ViewHolder {
        ImageView mImage;
        TextView mText;
        MovieViewHolder(View view) {
            super(view);
            mImage = view.findViewById(R.id.iv_movie_cover);
            mText = view.findViewById(R.id.tv_movie_title);
        }
    }

    MovieAdapter(List<Movie> movieList) {
        mMovies = movieList;
    }

    @NonNull
    @Override
    public MovieAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.rv_list_item, viewGroup, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.MovieViewHolder holder, int position) {
        holder.mImage.setImageBitmap(mMovies.get(position).getImages().getPosters().get(0).getImage());
        holder.mText.setText(mMovies.get(position).getTitle());
        holder.itemView.setId(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //raiseLevel();
            }
        });
    }

    @Override
    public int getItemCount() {
        if(mMovies == null) return 0;
        return mMovies.size();
    }

}