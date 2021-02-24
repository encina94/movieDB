package com.brubank.movies.connectivity.service.movies

import com.brubank.movies.connectivity.ApiResponse
import com.brubank.movies.connectivity.ErrorApiResponse
import com.brubank.movies.connectivity.Retrofit
import com.brubank.movies.connectivity.model.Configuration
import com.brubank.movies.connectivity.model.MovieDetail
import com.brubank.movies.connectivity.model.MovieGenreList
import com.brubank.movies.connectivity.model.MoviesList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.brubank.movies.extensions.api.parse

class MoviesService: IMoviesService {

    private val moviesApi: MoviesApi by lazy {
        Retrofit.getServiceRetrofit(MoviesApi::class.java)
    }

    override suspend fun getDiscoverMovies(): ApiResponse<MoviesList> {
        return withContext(Dispatchers.IO) {
            try {
                return@withContext moviesApi.getDiscoverMovies().parse()
            } catch (e: Exception) {
                return@withContext ErrorApiResponse(e.message)
            }
        }
    }

    override suspend fun getGenresMovies(): ApiResponse<MovieGenreList> {
        return withContext(Dispatchers.IO) {
            try {
                return@withContext moviesApi.getGenresMovies().parse()
            } catch (e: Exception) {
                return@withContext ErrorApiResponse(e.message)
            }
        }
    }

    override suspend fun searchMovie(word: String): ApiResponse<MoviesList> {
        return withContext(Dispatchers.IO) {
            try {
                return@withContext moviesApi.searchMovie(word).parse()
            } catch (e: Exception) {
                return@withContext ErrorApiResponse(e.message)
            }
        }
    }
    override suspend fun getConfiguration(): ApiResponse<Configuration> {
        return withContext(Dispatchers.IO) {
            try {
                return@withContext moviesApi.getConfiguration().parse()
            } catch (e: Exception) {
                return@withContext ErrorApiResponse(e.message)
            }
        }
    }

}