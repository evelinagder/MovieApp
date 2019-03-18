package com.example.moviesapp.app

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.service.MovieRepositoryImpl

class HomeFragmentViewModel : ViewModel() {


    val getListTrigger = MutableLiveData<Boolean>()
    val kidsMoviesResponse = Transformations.switchMap(getListTrigger) {
        MovieRepositoryImpl().getPopularKidsMovies()
    }


}