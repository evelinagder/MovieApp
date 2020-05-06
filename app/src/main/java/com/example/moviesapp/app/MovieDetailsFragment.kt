package com.example.moviesapp.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.moviesapp.R
import kotlinx.android.synthetic.main.fragment_movie_details.*

class MovieDetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_movie_details, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title =
            getString(R.string.details_screen_title)

        val viewModel: MovieDetailsFragmentViewModel by viewModels()

        arguments?.let {
            val movie = MovieDetailsFragmentArgs.fromBundle(it).movie
            details_description.text = movie.overview
            Glide.with(details_poster.context).load(movie.posterImageUrl)
                .into(details_poster)
            details_title.text = movie.title
        }
    }
}