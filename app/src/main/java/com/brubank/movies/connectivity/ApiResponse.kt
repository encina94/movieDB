package com.brubank.movies.connectivity

/**
 * This is a class used by API responses.
 */
sealed class ApiResponse<out Data>

data class EmptyApiResponse(val message: String) : ApiResponse<Nothing>()

data class SuccessApiResponse<out Data>(val body: Data) :
    ApiResponse<Data>()

data class ErrorApiResponse(val errorMessage: String? = "") :
    ApiResponse<Nothing>()