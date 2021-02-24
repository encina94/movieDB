package com.brubank.movies.connectivity.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieGenre (
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String
)