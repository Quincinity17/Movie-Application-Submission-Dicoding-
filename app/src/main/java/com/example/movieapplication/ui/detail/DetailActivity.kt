package com.example.movieapplication.ui.detail

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.movieapplication.data.Movie
import com.example.movieapplication.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        // Mengambil data Movie yang dikirim melalui intent
        val movie: Movie? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("EXTRA_MOVIE", Movie::class.java)
        } else {
            intent.getParcelableExtra("EXTRA_MOVIE")
        }

        // Jika movie null, keluar dari activity
        if (movie == null) {
            finish()
            return
        }

        // Menampilkan data Movie langsung dari objek Movie
        with(binding) {
            ivPoster.setImageResource(movie.imageRes)
            tvTitle.text = movie.title
            tvHeader.text = movie.title
            tvSynopsis.text = movie.synopsis
            tvIMDBRating.text = movie.imdbRating.toString()
            tvRottenTomatoesRating.text = movie.rottenTomatoes.toString()
            tvDuration.text = movie.duration
            tvGenre.text = movie.category
        }

        // Tombol kembali
        binding.btnBack.setOnClickListener { onBackPressedDispatcher.onBackPressed() }

        binding.actionShare.setOnClickListener {
            shareMovie(movie)
        }
    }

    private fun shareMovie(movie: Movie) {
        val shareText = "Check out this movie: ${movie.title}!\nDuration: ${movie.duration}\nIMDB: ${movie.imdbRating}/10\nRotten Tomatoes: ${movie.rottenTomatoes}%\nSynopsis: ${movie.synopsis}"

        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, shareText)
            type = "text/plain"
        }
        startActivity(Intent.createChooser(shareIntent, "Share via"))
    }
}
