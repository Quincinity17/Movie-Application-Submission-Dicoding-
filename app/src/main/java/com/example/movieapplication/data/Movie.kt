package com.example.movieapplication.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val title: String,
    val duration: String,
    val category: String,
    val imageRes: Int,
    val imdbRating: Double,
    val rottenTomatoes: Double,
    val synopsis: String
) : Parcelable
