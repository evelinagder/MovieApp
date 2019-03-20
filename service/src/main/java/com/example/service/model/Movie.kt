package com.example.service.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w185"

@Parcelize
data class Movie(
    @SerializedName("title") val title: String,
    @SerializedName("poster_path") private val posterUrl: String,
    @SerializedName("overview") val overview: String
) : Parcelable {

    val posterImageUrl: String
        get() = BASE_IMAGE_URL + posterUrl
}