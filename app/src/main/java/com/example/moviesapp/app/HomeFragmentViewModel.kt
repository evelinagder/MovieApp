package com.example.moviesapp.app

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.service.MovieRepositoryImpl

class HomeFragmentViewModel : ViewModel() {


    private val repository = MovieRepositoryImpl()
    val getListTrigger = MutableLiveData<Boolean>()
    val moviesResponse = Transformations.switchMap(getListTrigger) {
        repository.getPopularKidsMovies()
    }
}