package com.example.movieslibrary

import android.os.Bundle
import com.example.moviesapp.view.BaseActivity

class MovieLibraryActivity :
    BaseActivity<com.example.movieslibrary.databinding.ActivityMovieLibraryBinding, MovieLibraryActivityViewModel>() {

    override fun getViewModelResId(): Int = BR.movieLibraryActivityVM

    override fun getViewModelClass(): Class<MovieLibraryActivityViewModel> =
        MovieLibraryActivityViewModel::class.java

    override fun getLayoutResId(): Int = R.layout.activity_movie_library

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

}