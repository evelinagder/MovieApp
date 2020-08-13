package com.example.service

import androidx.lifecycle.LiveData
import com.example.service.model.ApiResponse
import com.example.service.model.KidsMoviesResponse
import retrofit2.http.GET

interface MoviesService {

    @GET("movie/popular?api_key=9793e782a0e0496fff7f67540c038a37&language=en-US&page=1")

    fun getPopularKidsMovies(): LiveData<ApiResponse<KidsMoviesResponse>>
}