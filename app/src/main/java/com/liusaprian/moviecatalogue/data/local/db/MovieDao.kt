package com.liusaprian.moviecatalogue.data.local.db

import androidx.paging.DataSource
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import com.liusaprian.moviecatalogue.model.Movie

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<Movie>)

    @RawQuery(observedEntities = [Movie::class])
    fun getMovies(query: SupportSQLiteQuery): DataSource.Factory<Int, Movie>

    @Query("SELECT * FROM movies WHERE isFavorite = 1 AND isMovie = 1")
    fun getFavoriteMovies(): DataSource.Factory<Int, Movie>

    @Update
    fun updateMovie(movie: Movie)

    @RawQuery(observedEntities = [Movie::class])
    fun getTvShows(query: SupportSQLiteQuery): DataSource.Factory<Int, Movie>

    @Query("SELECT * FROM movies WHERE isFavorite = 1 AND isMovie = 0")
    fun getFavoriteTvShows(): DataSource.Factory<Int, Movie>
}