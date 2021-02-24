package com.brubank.movies.viewmodel.movies

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.brubank.movies.connectivity.EmptyApiResponse
import com.brubank.movies.connectivity.ErrorApiResponse
import com.brubank.movies.connectivity.SuccessApiResponse
import com.brubank.movies.connectivity.model.Configuration
import com.brubank.movies.connectivity.model.Movie
import com.brubank.movies.connectivity.model.MovieGenre
import com.brubank.movies.connectivity.model.MovieGenreList
import com.brubank.movies.connectivity.model.MoviesList
import com.brubank.movies.connectivity.service.movies.MoviesService
import com.brubank.movies.utils.Constants.Companion.CONFIGURATION_SUCCESS
import com.brubank.movies.utils.Constants.Companion.GENRE_SUCCESS
import com.brubank.movies.viewmodel.EmptyResponse
import com.brubank.movies.viewmodel.ErrorResponse
import com.brubank.movies.viewmodel.Loading
import com.brubank.movies.viewmodel.SuccessResponse
import com.brubank.movies.viewmodel.ViewModelResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MoviesViewModel: ViewModel(), CoroutineScope {


    lateinit var baseUrlImage:String
    lateinit var sizeImage: String
    lateinit var listGenre: List<MovieGenre>
    var loadInitData: Boolean = true

    val configurationLiveData: MutableLiveData<ViewModelResponse<Configuration>> = MutableLiveData()
    val movieListLiveData: MutableLiveData<ViewModelResponse<MoviesList>> =
        MutableLiveData()
    val movieDetail: MutableLiveData<Movie> = MutableLiveData()
    val movieGenreList: MutableLiveData<ViewModelResponse<MovieGenreList>> =
        MutableLiveData()
    val initialDataLoadLiveData: MutableLiveData<HashMap<String, Boolean>> = MutableLiveData(hashMapOf(CONFIGURATION_SUCCESS to false, GENRE_SUCCESS to false))

    fun getConfiguration(){
        launch {
            configurationLiveData.postValue(Loading(true))
            when(val apiResponse = MoviesService().getConfiguration()){
                is EmptyApiResponse-> {
                    configurationLiveData.postValue(EmptyResponse())
                }
                is SuccessApiResponse -> {
                    configurationLiveData.postValue(SuccessResponse(apiResponse.body))
                }
                is ErrorApiResponse -> {
                    configurationLiveData.postValue(ErrorResponse(apiResponse.errorMessage))
                }
            }
        }
    }

    fun getMovies(){
        launch {
            movieListLiveData.postValue(Loading(true))
            when(val apiResponse = MoviesService().getDiscoverMovies()){
                is EmptyApiResponse-> {
                    movieListLiveData.postValue(EmptyResponse())
                }
                is SuccessApiResponse -> {
                    movieListLiveData.postValue(SuccessResponse(apiResponse.body))
                }
                is ErrorApiResponse -> {
                    movieListLiveData.postValue(ErrorResponse(apiResponse.errorMessage))
                }
            }
        }
    }
    fun getGenre(){
        launch {
            movieGenreList.postValue(Loading(true))
            when(val apiResponse = MoviesService().getGenresMovies()){
                is EmptyApiResponse-> {
                    movieGenreList.postValue(EmptyResponse())
                }
                is SuccessApiResponse -> {
                    movieGenreList.postValue(SuccessResponse(apiResponse.body))
                }
                is ErrorApiResponse -> {
                    movieGenreList.postValue(ErrorResponse(apiResponse.errorMessage))
                }
            }
        }
    }

    override val coroutineContext: CoroutineContext = Job()
}