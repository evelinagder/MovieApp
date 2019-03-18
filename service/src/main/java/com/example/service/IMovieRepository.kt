package com.example.service

import androidx.lifecycle.LiveData
import com.example.service.model.Movie
import com.example.service.model.Resource

interface IMovieRepository {

    fun getPopularKidsMovies(): LiveData<Resource<List<Movie>>>
}