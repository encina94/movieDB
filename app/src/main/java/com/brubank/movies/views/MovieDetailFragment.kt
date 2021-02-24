package com.brubank.movies.views

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.brubank.movies.R
import com.brubank.movies.databinding.FragmentMovieDetailBinding
import com.brubank.movies.extensions.loadImageFromUrl
import com.brubank.movies.utils.ImageUtils
import com.brubank.movies.viewmodel.movies.MoviesViewModel

class MovieDetailFragment: Fragment() {
    /**
     * The viewmodel is placed at the activity level so that the rest of the fragments can use the data obtained
     */
    private val moviesViewModel: MoviesViewModel by lazy {
        ViewModelProvider(requireActivity()).get(MoviesViewModel::class.java)
    }
    private lateinit var binding: FragmentMovieDetailBinding
    private var subscribed = false


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieDetailBinding.inflate(layoutInflater, container, false)
        val navController = findNavController()
        binding.detailToolbar.setupWithNavController(navController)
        binding.detailToolbar.title = ""
        with(moviesViewModel) {
            binding.movieDetailImage.loadImageFromUrl(
                ImageUtils.getUrlImage(baseUrlImage, sizeImage,movieDetail.value?.posterPath.toString()),
                requireContext())
            binding.contentMovieDetail.text = movieDetail.value?.overview
            binding.titleMovieDetail.text = movieDetail.value?.title
            binding.dateMovieDetail.text = movieDetail.value?.releaseDate?.substringBefore("-")
        }
            binding.buttonSubscribe.setOnClickListener {
                if(!subscribed){
                    binding.buttonSubscribe.background = ContextCompat.getDrawable(requireContext(), R.drawable.oval_button_subscribed)
                    binding.buttonSubscribe.text = getString(R.string.button_movie_detail_subscribed)
                    binding.buttonSubscribe.setTextColor(Color.BLACK)
                    subscribed = true
                } else {
                    binding.buttonSubscribe.background = ContextCompat.getDrawable(requireContext(), R.drawable.oval_button)
                    binding.buttonSubscribe.text= getString(R.string.button_movie_detail_subscribe)
                    binding.buttonSubscribe.setTextColor(Color.WHITE)
                    subscribed = false
                }

            }

        return binding.root
    }
}