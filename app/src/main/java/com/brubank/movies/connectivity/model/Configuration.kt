package com.brubank.movies.connectivity.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Configuration(
    @Json(name = "images")
    val images: Images,
    @Json(name = "change_keys")
    val change_keys: List<String>
) {
}