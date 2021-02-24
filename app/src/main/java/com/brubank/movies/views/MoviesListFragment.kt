package com.brubank.movies.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.brubank.movies.R
import com.brubank.movies.connectivity.model.Movie
import com.brubank.movies.connectivity.model.MoviesList
import com.brubank.movies.databinding.FragmentMoviesListBinding
import com.brubank.movies.utils.Constants
import com.brubank.movies.utils.Constants.Companion.CONFIGURATION_SUCCESS
import com.brubank.movies.utils.Constants.Companion.GENRE_SUCCESS
import com.brubank.movies.viewmodel.EmptyResponse
import com.brubank.movies.viewmodel.ErrorResponse
import com.brubank.movies.viewmodel.Loading
import com.brubank.movies.viewmodel.SuccessResponse
import com.brubank.movies.viewmodel.movies.MoviesViewModel
import com.brubank.movies.views.adapter.MoviesAdapter

class MoviesListFragment: Fragment(), MoviesAdapter.OnServiceClickListener {
    /**
     * The viewmodel is placed at the activity level so that the rest of the fragments can use the data obtained
     */
    private val moviesViewModel: MoviesViewModel by lazy {
        ViewModelProvider(requireActivity()).get(MoviesViewModel::class.java)
    }
    private lateinit var binding: FragmentMoviesListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMoviesListBinding.inflate(layoutInflater, container, false)
        binding.itemList.layoutManager = LinearLayoutManager(requireContext())
        binding.itemList.adapter = MoviesAdapter(emptyList(), requireContext(),this)

        loadInitialData()

        moviesViewModel.movieGenreList.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Loading -> {

                }
                is SuccessResponse -> {
                    //It takes the list of genres, stores it in the viewmodel to persist and then updates the initial data dictionary.
                    moviesViewModel.listGenre = response.body.genres
                    moviesViewModel.initialDataLoadLiveData.apply {
                        value?.put(GENRE_SUCCESS, true)
                        this.value = value
                    }

                }
                is EmptyResponse -> {
                }
                is ErrorResponse -> {
                }
            }
        })

        moviesViewModel.configurationLiveData.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Loading -> {

                }
                is SuccessResponse -> {
                    //Take the part of the image base url and take the w300 from the size array.
                    // Then it loads them into the viewModel and updates the initial load dictionary.
                    var sizeImage = ""
                    moviesViewModel.baseUrlImage = response.body.images.secure_base_url
                    response.body.images.backdrop_sizes.let {
                        sizeImage = if(it.contains(Constants.SIZE_IMAGE)){
                            Constants.SIZE_IMAGE
                        } else {
                            it.lastOrNull().toString()
                        }
                        moviesViewModel.sizeImage = sizeImage
                    }
                    moviesViewModel.initialDataLoadLiveData.apply {
                        value?.put(CONFIGURATION_SUCCESS, true)
                        this.value = value
                    }
                }
                is EmptyResponse -> {
                }
                is ErrorResponse -> {
                }
            }
        })

        /**
         * If the list of genres and settings were already loaded then the movie enpoint is called there
         */

        moviesViewModel.initialDataLoadLiveData.observe(viewLifecycleOwner, Observer { data ->
           if(data[GENRE_SUCCESS]!! && data[CONFIGURATION_SUCCESS]!!){
               moviesViewModel.getMovies()
           }
        })


        /**
         * Once the movies are loaded, the necessary data is sent to the adapter to load them in the recyclerview
         */
        moviesViewModel.movieListLiveData.observe(viewLifecycleOwner, Observer { response ->
            when(response) {
                is Loading -> {
                }
                is SuccessResponse -> {
                    refreshMoviesList(response.body)
                }
                is EmptyResponse -> {
                }
                is ErrorResponse -> {
                }
            }
        })
        return binding.root
    }

    private fun refreshMoviesList(movieList: MoviesList) {
        if (movieList.results.isNotEmpty()) {
            with(binding.itemList.adapter as MoviesAdapter){
                this.listMovies = movieList.results
                this.baseUrl = moviesViewModel.baseUrlImage
                this.sizeImage = moviesViewModel.sizeImage
                this.genres = moviesViewModel.listGenre
            }
            binding.itemList.adapter?.notifyDataSetChanged()
        }
    }

    /**
     * The initial data is loaded only once and is already loaded in the viewModel
     */
    private fun loadInitialData() {
        if(moviesViewModel.loadInitData){
            moviesViewModel.getConfiguration()
            moviesViewModel.getGenre()
            moviesViewModel.loadInitData = false
        }

    }

    override fun onItemClick(movie: Movie) {
        moviesViewModel.movieDetail.value = movie
        findNavController().navigate(R.id.action_movie_detail)
    }
}