package com.example.moviesapp.app

import android.os.Bundle
import android.view.View
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

          // todo navigation safe arguments
    }

}