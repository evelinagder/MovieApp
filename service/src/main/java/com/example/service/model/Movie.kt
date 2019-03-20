package com.example.service.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    @SerializedName("title") val title: String,
    @SerializedName("poster_path") val posterUrl: String,
    @SerializedName("overview") val overview: String) : Parcelable