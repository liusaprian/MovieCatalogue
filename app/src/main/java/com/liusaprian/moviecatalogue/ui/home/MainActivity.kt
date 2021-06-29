package com.liusaprian.moviecatalogue.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.StringRes
import com.google.android.material.tabs.TabLayoutMediator
import com.liusaprian.moviecatalogue.R
import com.liusaprian.moviecatalogue.adapter.MainSectionsPagerAdapter
import com.liusaprian.moviecatalogue.databinding.ActivityMainBinding
import com.liusaprian.moviecatalogue.ui.favorite.FavoriteActivity
import com.liusaprian.moviecatalogue.viewmodel.MovieViewModel
import com.liusaprian.moviecatalogue.viewmodel.ViewModelFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    companion object {
        @StringRes
        val TAB_TITLES = intArrayOf(R.string.movies, R.string.tv_shows)
    }

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var viewModel: MovieViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sectionsPagerAdapter = MainSectionsPagerAdapter(this)
        binding.viewPager.adapter = sectionsPagerAdapter
        with(binding) {
            TabLayoutMediator(tabs, viewPager) {tab, position ->
                tab.text = resources.getString(TAB_TITLES[position])
            }.attach()
        }

        supportActionBar?.elevation = 0f
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.favorite_page -> startActivity(Intent(this, FavoriteActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }
}