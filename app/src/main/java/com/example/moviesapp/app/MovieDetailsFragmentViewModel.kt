package com.example.moviesapp.app

import android.graphics.drawable.Drawable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MovieDetailsFragmentViewModel : ViewModel() {

    val movieTitle = MutableLiveData<String>()
    val movieDescription = MutableLiveData<String>()
    val moviePoster = MutableLiveData<Drawable>()
}