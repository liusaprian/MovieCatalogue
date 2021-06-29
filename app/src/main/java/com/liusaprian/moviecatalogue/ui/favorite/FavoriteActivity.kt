package com.liusaprian.moviecatalogue.ui.favorite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.google.android.material.tabs.TabLayoutMediator
import com.liusaprian.moviecatalogue.R
import com.liusaprian.moviecatalogue.adapter.FavoriteSectionsPagerAdapter
import com.liusaprian.moviecatalogue.databinding.ActivityFavoriteBinding
import com.liusaprian.moviecatalogue.ui.home.MainActivity.Companion.TAB_TITLES

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sectionsPagerAdapter = FavoriteSectionsPagerAdapter(this)
        binding.viewPager.adapter = sectionsPagerAdapter
        with(binding) {
            TabLayoutMediator(tabs, viewPager) {tab, position ->
                tab.text = resources.getString(TAB_TITLES[position])
            }.attach()
        }

        supportActionBar?.elevation = 0f
        supportActionBar?.title = resources.getString(R.string.favorite_movies)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}