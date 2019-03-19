package com.example.service

import androidx.lifecycle.LiveData
import com.example.service.model.KidsMoviesResponse
import com.example.service.model.Resource

interface IMovieRepository {

    fun getPopularKidsMovies(): LiveData<Resource<KidsMoviesResponse>>
}