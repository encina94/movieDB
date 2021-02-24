package com.brubank.movies.connectivity.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieDetail (
    @Json(name = "id")
    val id: Int? = 0,
    @Json(name ="title")
    val title: String? = "",
    @Json(name ="runtime")
    val runtime: Int? = 0,
    @Json(name ="votesCount")
    val votesCount: Int? = 0,
    @Json(name ="genres")
    val genres: List<MovieGenre>? = emptyList(),
    @Json(name ="poster")
    val poster: String? = "",
    @Json(name ="overview")
    val overview: String? = ""
)