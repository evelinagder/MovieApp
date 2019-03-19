package com.example.service.model

import com.google.gson.annotations.SerializedName

data class KidsMoviesResponse(@SerializedName("results") val results: List<Movie>)