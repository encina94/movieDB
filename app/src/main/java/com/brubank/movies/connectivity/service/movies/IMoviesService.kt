package com.brubank.movies.connectivity.service.movies

import com.brubank.movies.connectivity.ApiResponse
import com.brubank.movies.connectivity.model.Configuration
import com.brubank.movies.connectivity.model.MovieDetail
import com.brubank.movies.connectivity.model.MovieGenreList
import com.brubank.movies.connectivity.model.MoviesList

interface IMoviesService {

    suspend fun getDiscoverMovies(): ApiResponse<MoviesList>

    suspend fun getGenresMovies(): ApiResponse<MovieGenreList>

    suspend fun searchMovie(word: String): ApiResponse<MoviesList>

    suspend fun getConfiguration(): ApiResponse<Configuration>
}