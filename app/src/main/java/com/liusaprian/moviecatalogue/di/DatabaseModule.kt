package com.liusaprian.moviecatalogue.di

import android.content.Context
import androidx.room.Room
import com.liusaprian.moviecatalogue.data.local.db.MovieDao
import com.liusaprian.moviecatalogue.data.local.db.MovieDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun provideMovieDatabase(context: Context): MovieDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            MovieDatabase::class.java,
            "Movies.db")
            .build()
    }

    @Singleton
    @Provides
    fun provideMovieDao(database: MovieDatabase): MovieDao {
        return database.movieDao()
    }
}