package com.liusaprian.moviecatalogue.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.core.content.ContextCompat
import com.liusaprian.moviecatalogue.R
import com.liusaprian.moviecatalogue.databinding.ActivitySplashBinding
import com.liusaprian.moviecatalogue.ui.home.MainActivity

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val splashScreenText = SpannableString(getString(R.string.app_name))
        splashScreenText.setSpan(ForegroundColorSpan(ContextCompat.getColor(this, R.color.colorPrimaryLight)), 0,5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.splashText.text = splashScreenText

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 1200)
    }
}