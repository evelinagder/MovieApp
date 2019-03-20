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
import com.example.moviesapp.databinding.ItemMovieBinding
import com.example.service.model.Movie

const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w185"

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

        private val options = navOptions {
            anim {
                enter = R.anim.slide_in_right
                exit = R.anim.slide_out_left
                popEnter = R.anim.slide_in_left
                popExit = R.anim.slide_out_right
            }
        }

        fun update(movie: Movie?) {

            binding.itemMovieTitle.text = movie?.title
            Glide.with(binding.itemMovieImage.context)
                .load(BASE_IMAGE_URL + movie?.posterUrl)
                .into(binding.itemMovieImage)
            binding.root.setOnClickListener {
                Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_placeholder, null, options)
            }
        }
    }
}