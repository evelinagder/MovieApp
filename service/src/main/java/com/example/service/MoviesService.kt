package com.example.service

import androidx.lifecycle.LiveData
import com.example.service.model.ApiResponse
import com.example.service.model.Movie
import retrofit2.http.GET

interface MoviesService {

    @GET("/discover/movie?certification_country=US&certification=R&sort_by=vote_average.desc?api_key=07a4979bf41d8c39a9be0358a4b1ca3a")
    fun getPopularKidsMovies(): LiveData<ApiResponse<List<Movie>>>
}