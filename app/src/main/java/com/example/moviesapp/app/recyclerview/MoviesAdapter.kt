package com.example.moviesapp.app.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.navigation.navOptions
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviesapp.R
import com.example.moviesapp.app.HomeFragmentDirections
import com.example.moviesapp.databinding.ItemMovieBinding
import com.example.service.model.Movie



class MoviesAdapter(private val movies: List<Movie>?) : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMovieBinding.inflate(inflater, parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        movies?.let {
            holder.update(movies[position])
        }
    }

    override fun getItemCount(): Int = movies?.size ?: 0


    class MovieViewHolder(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {

        fun update(movie: Movie?) {

            binding.itemMovieTitle.text = movie?.title
            Glide.with(binding.itemMovieImage.context)
                .load(movie?.posterImageUrl)
                .into(binding.itemMovieImage)
            binding.root.setOnClickListener {
                //pass the movie as safeArgs to DetailsFragment
                movie?.let { movie ->
                    val navigationDirection = HomeFragmentDirections.actionHomeFragmentToPlaceholder(movie)
                    Navigation.findNavController(it).navigate(navigationDirection)
                }
            }
        }
    }
}