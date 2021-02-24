package com.brubank.movies.connectivity.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MoviesList (
    @Json(name = "page")
    val page: Int? = 0,
    @Json(name = "totalPages")
    val totalPages: Int? = 0,
    @Json(name = "totalResults")
    val totalResults: Int? = 0,
    @Json(name = "results")
    val results: List<Movie> = emptyList()
)