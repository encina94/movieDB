package com.brubank.movies.extensions.api

import com.brubank.movies.connectivity.ApiResponse
import com.brubank.movies.connectivity.EmptyApiResponse
import com.brubank.movies.connectivity.ErrorApiResponse
import com.brubank.movies.connectivity.SuccessApiResponse
import retrofit2.Response

/**
 * Evaluate the status code
 * and returns an ApiResponse<Data>
 */

fun <Data> Response<Data>.parse(): ApiResponse<Data> {
        return when (this.code()) {
            200 -> {
                this.body()?.let {
                    data -> SuccessApiResponse(data)
                } ?: run {
                    EmptyApiResponse("Empty response")
                }
            }
            401 -> {
                ErrorApiResponse("Unauthorized")
            }
            404 -> {
                ErrorApiResponse("Not found")
            }
            else -> {
                EmptyApiResponse("")
            }
        }
}