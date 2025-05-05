package com.example.movieapplication.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapplication.data.Movie
import com.example.movieapplication.databinding.ItemMovieBinding

class MovieAdapter     (
    private val onItemClick: (Movie) -> Unit
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private val movieList = mutableListOf<Movie>()

    inner class MovieViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            binding.imgMovie.setImageResource(movie.imageRes)
            binding.tvMovieTitle.text = movie.title
            binding.tvDuration.text = movie.duration
            binding.tvCategory.text = movie.category

            // Menambahkan event klik pada item
            binding.root.setOnClickListener {
                onItemClick(movie)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movieList[position])
    }

    override fun getItemCount() = movieList.size

    fun setData(newMovies: List<Movie>) {
        movieList.clear()
        movieList.addAll(newMovies)
        notifyDataSetChanged()
    }
}