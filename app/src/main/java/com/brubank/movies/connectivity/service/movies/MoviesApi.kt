package com.brubank.movies.connectivity.service.movies

import com.brubank.movies.connectivity.model.Configuration
import com.brubank.movies.connectivity.model.MovieDetail
import com.brubank.movies.connectivity.model.MovieGenreList
import com.brubank.movies.connectivity.model.MoviesList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApi {
   /**
     * Discover movies by different types of data like average rating, number of votes, genres and certifications
     */
    @GET("discover/movie")
    suspend fun getDiscoverMovies(@Query("sort_by") sortBy: String = "popularity.desc"): Response<MoviesList>

   /**
    * Get the list of official genres for movies.
    */
   @GET("genre/movie/list")
   suspend fun getGenresMovies(): Response<MovieGenreList>

   /**
    * Searchs for movies.
    */

    @GET("search/movie")
    suspend fun searchMovie(@Query("query") query: String, @Query("include_adult") includeAdult: Boolean = false): Response<MoviesList>


    @GET("configuration")
    suspend fun getConfiguration(): Response<Configuration>



}