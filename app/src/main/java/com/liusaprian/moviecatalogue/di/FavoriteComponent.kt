package com.liusaprian.moviecatalogue.di

import com.liusaprian.moviecatalogue.ui.favorite.FavoriteMovieFragment
import dagger.Subcomponent

@ActivityScope
@Subcomponent
interface FavoriteComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): FavoriteComponent
    }

    fun inject(fragment: FavoriteMovieFragment)
}