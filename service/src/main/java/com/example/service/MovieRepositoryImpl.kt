package com.example.service

import androidx.lifecycle.LiveData
import com.example.service.model.Movie
import com.example.service.model.NetworkBoundResource
import com.example.service.model.Resource

class MovieRepositoryImpl() : IMovieRepository {


    val api = RetrofitClientInstance.retrofitInstance?.create(MoviesService::class.java)

    override fun getPopularKidsMovies(): LiveData<Resource<List<Movie>>> {

        return object : NetworkBoundResource<List<Movie>>() {

            override fun createCall() = api?.getPopularKidsMovies()

        }.asLiveData()
    }
}