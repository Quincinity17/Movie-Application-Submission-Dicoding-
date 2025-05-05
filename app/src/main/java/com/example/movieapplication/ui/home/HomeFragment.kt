package com.example.movieapplication.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieapplication.data.MovieData
import com.example.movieapplication.databinding.FragmentHomeBinding
import com.example.movieapplication.ui.adapter.MovieAdapter
import com.example.movieapplication.ui.detail.DetailActivity
import com.example.movieapplication.data.Movie // Pastikan Movie class di-import

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var movieAdapter: MovieAdapter

    // Property hanya valid antara onCreateView dan onDestroyView
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setupRecyclerView()
        setupTrendingMovieClick() // Tambahkan fungsi klik untuk trending movie

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRecyclerView() {
        // Inisialisasi adapter dengan event klik
        movieAdapter = MovieAdapter { movie ->
            val intent = Intent(requireContext(), DetailActivity::class.java).apply {
                putExtra("EXTRA_MOVIE", movie)
            }

            Log.d("DetailActivity", "Sending movie: $movie") // Debug log
            startActivity(intent)
        }

        // Atur RecyclerView
        binding.rvBoxOffice.apply {
            layoutManager = GridLayoutManager(context, 2) // 2 kolom
            adapter = movieAdapter
        }

        // Set data ke adapter
        movieAdapter.setData(MovieData.movies)
    }

    private fun setupTrendingMovieClick() {
        // Cari data movie "Up" dari list
        val movieUp = MovieData.movies.find { it.title == "Up" }

        // Jika ditemukan, atur event klik
        movieUp?.let { movie ->
            binding.imgTrendingMovie.setOnClickListener {
                val intent = Intent(requireContext(), DetailActivity::class.java).apply {
                    putExtra("EXTRA_MOVIE", movie)
                }

                Log.d("DetailActivity", "Clicked Trending Movie: $movie") // Debugging
                startActivity(intent)
            }
        }
    }
}
