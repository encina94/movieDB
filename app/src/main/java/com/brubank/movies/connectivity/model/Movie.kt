package com.brubank.movies.connectivity.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Movie (
    @Json(name = "id")
    val id: Int? = 0,
    @Json(name = "title")
    val title: String? = "",
    @Json(name = "poster_path")
    val posterPath: String? = "",
    @Json(name = "backdrop_path")
    val backdropPath:String? = "",
    @Json(name = "voteAverage")
    val voteAverage: Double? = 0.0,
    @Json(name = "release_date")
    val releaseDate: String? = "",
    @Json(name = "genre_ids")
    val genreIds: List<Int>? = emptyList(),
    @Json(name ="overview")
    val overview: String? = ""
)