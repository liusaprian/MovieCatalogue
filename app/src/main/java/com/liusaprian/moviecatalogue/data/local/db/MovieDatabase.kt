package com.liusaprian.moviecatalogue.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.liusaprian.moviecatalogue.model.Movie

@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}