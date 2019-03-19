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
import com.example.service.model.Movie

const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w185"

class MoviesAdapter(private val movies: List<Movie>?) : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        movies?.let {
            holder.update(movies[position])
        }
    }

    override fun getItemCount(): Int = movies?.size ?: 0


    class MovieViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        private val options = navOptions {
            anim {
                enter = R.anim.slide_in_right
                exit = R.anim.slide_out_left
            }
        }

        fun update(movie: Movie?) {

            view.findViewById<TextView>(R.id.item_movie_title).text = movie?.title
            val imageView = view.findViewById<ImageView>(R.id.item_movie_image)
            Glide.with(view.context)
                .load(BASE_IMAGE_URL + movie?.posterUrl)
                .into(imageView)
            view.setOnClickListener {
                Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_placeholder, null, options)
            }
        }
    }
}