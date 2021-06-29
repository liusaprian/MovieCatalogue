package com.liusaprian.moviecatalogue.adapter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.liusaprian.moviecatalogue.ui.home.MovieFragment
import com.liusaprian.moviecatalogue.utils.ResponseConfig

class MainSectionsPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment =
        when (position) {
            0 -> {
                val movieFragment = MovieFragment()
                val bundle = Bundle()
                bundle.putString(MovieFragment.TYPE, ResponseConfig.MOVIE)
                movieFragment.arguments = bundle
                movieFragment
            }
            1 -> {
                val tvShowFragment = MovieFragment()
                val bundle = Bundle()
                bundle.putString(MovieFragment.TYPE, ResponseConfig.TV)
                tvShowFragment.arguments = bundle
                tvShowFragment
            }
            else -> Fragment()
        }
}
