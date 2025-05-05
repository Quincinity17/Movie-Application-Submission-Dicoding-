package com.example.movieapplication.ui.splash

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.movieapplication.R


import android.content.Intent
import android.os.Handler
import android.os.Looper
import com.example.movieapplication.databinding.ActivitySplashBinding
import com.example.movieapplication.ui.MainActivity

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        // Play animasi Lottie
        binding.lottieAnimationView.playAnimation()

        // Delay sebelum berpindah ke MainActivity
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // Tutup SplashActivity agar tidak bisa kembali
        }, 3000) // Animasi berjalan selama 3 detik
    }
}