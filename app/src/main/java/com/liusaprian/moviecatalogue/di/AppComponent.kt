package com.liusaprian.moviecatalogue.di

import android.content.Context
import com.liusaprian.moviecatalogue.data.MovieRepository
import com.liusaprian.moviecatalogue.ui.detail.MovieDetailActivity
import com.liusaprian.moviecatalogue.ui.home.MovieFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DatabaseModule::class, AppSubComponents::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun getRepository(): MovieRepository

    fun inject(fragment: MovieFragment)
    fun inject(activity: MovieDetailActivity)
    fun favoriteComponent(): FavoriteComponent.Factory
}