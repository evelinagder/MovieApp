package com.example.service.model

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("title") val title: String,
    @SerializedName("poster_path") val posterUrl: String,
    @SerializedName("overview") val overview: String
)