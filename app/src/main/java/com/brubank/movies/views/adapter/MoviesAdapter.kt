package com.brubank.movies.views.adapter

import android.content.Context
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.brubank.movies.connectivity.model.Movie
import com.brubank.movies.connectivity.model.MovieGenre
import com.brubank.movies.databinding.ItemListContentBinding
import com.brubank.movies.extensions.loadImageFromUrl
import com.brubank.movies.utils.ImageUtils

class MoviesAdapter(var listMovies: List<Movie>, private val context: Context, private val itemClickListener: OnServiceClickListener) : RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>()  {
    lateinit var baseUrl:String
    lateinit var sizeImage: String
    lateinit var genres: List<MovieGenre>

    interface OnServiceClickListener {
        fun onItemClick(movie: Movie)
    }

    inner class MoviesViewHolder(binding: ItemListContentBinding) : RecyclerView.ViewHolder(binding.root) {
        val title: TextView = binding.titleMovie
        val image: ImageView = binding.movieImage
        val genre: TextView = binding.genre
        val cardView: CardView = binding.moviesCard
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        return MoviesViewHolder(ItemListContentBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movie = listMovies[position]
        holder.title.text = movie.title
        holder.genre.text = movie.genreIds!![0].toString()
        holder.image.loadImageFromUrl(ImageUtils.getUrlImage(baseUrl, sizeImage, movie.backdropPath .toString()), context)
        holder.genre.text = genres.find { it.id == movie.genreIds[0] }?.name
       // https://stackoverflow.com/questions/30340591/changing-an-imageview-to-black-and-white/30340661
        holder.image.colorFilter = ColorMatrixColorFilter(ColorMatrix().apply { setSaturation(0f)})
        holder.itemView.setOnClickListener{
            itemClickListener.onItemClick(movie)
        }
    }

    override fun getItemCount(): Int = listMovies.size


}