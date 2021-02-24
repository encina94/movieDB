package com.brubank.movies.connectivity.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieGenreList (
    @Json(name = "genres")
    val genres: List<MovieGenre>
)