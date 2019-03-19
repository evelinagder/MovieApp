package com.example.service

import androidx.lifecycle.LiveData
import com.example.service.model.KidsMoviesResponse
import com.example.service.model.NetworkBoundResource
import com.example.service.model.Resource

class MovieRepositoryImpl() : IMovieRepository {


    val api = RetrofitClientInstance.retrofitInstance?.create(MoviesService::class.java)

    override fun getPopularKidsMovies(): LiveData<Resource<KidsMoviesResponse>> {

        return object : NetworkBoundResource<KidsMoviesResponse>() {

            override fun createCall() = api?.getPopularKidsMovies()

        }.asLiveData()
    }
}