package com.brubank.movies.viewmodel

sealed class ViewModelResponse<T>

data class Loading<T>(val boolean:Boolean): ViewModelResponse<T>()

data class SuccessResponse<T>(val body: T): ViewModelResponse<T>()

class EmptyResponse<T>: ViewModelResponse<T>()

data class ErrorResponse<T>(val errorMessage: String?): ViewModelResponse<T>()