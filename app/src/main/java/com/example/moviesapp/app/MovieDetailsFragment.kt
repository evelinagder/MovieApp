package com.example.moviesapp.app

import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.example.moviesapp.BR
import com.example.moviesapp.R
import com.example.moviesapp.view.BaseFragment

class MovieDetailsFragment :
    BaseFragment<com.example.moviesapp.databinding.FragmentMovieDetailsBinding, MovieDetailsFragmentViewModel>() {

    override fun getViewModelResId(): Int = BR.detailsFragmentVM

    override fun getLayoutResId(): Int = R.layout.fragment_movie_details

    override fun getViewModelClass(): Class<MovieDetailsFragmentViewModel> = MovieDetailsFragmentViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            val movie = MovieDetailsFragmentArgs.fromBundle(it).movie
            viewModel.movieDescription.value = movie.overview
            Glide.with(binding.detailsPoster.context).load(movie.posterImageUrl).into(binding.detailsPoster)
            viewModel.movieTitle.value = movie.title
        }
    }
}