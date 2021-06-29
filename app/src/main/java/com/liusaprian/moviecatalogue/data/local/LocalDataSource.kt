package com.liusaprian.moviecatalogue.data.local

import androidx.paging.DataSource
import com.liusaprian.moviecatalogue.data.local.db.MovieDao
import com.liusaprian.moviecatalogue.model.Movie
import com.liusaprian.moviecatalogue.utils.ResponseConfig.Companion.MOVIE
import com.liusaprian.moviecatalogue.utils.ResponseConfig.Companion.TV
import com.liusaprian.moviecatalogue.utils.SortUtils
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val movieDao: MovieDao){

    fun getMovies(sort: String): DataSource.Factory<Int, Movie> {
        val query = SortUtils.getSortedQuery(MOVIE, sort)
        return movieDao.getMovies(query)
    }

    fun getFavoriteMovies(): DataSource.Factory<Int, Movie> = movieDao.getFavoriteMovies()

    fun getTvShows(sort: String): DataSource.Factory<Int, Movie> {
        val query = SortUtils.getSortedQuery(TV, sort)
        return movieDao.getTvShows(query)
    }

    fun getFavoriteTvShows(): DataSource.Factory<Int, Movie> = movieDao.getFavoriteTvShows()

    fun insertMovies(movies: List<Movie>) = movieDao.insertMovies(movies)

    fun switchFavoriteState(movie: Movie) = movieDao.updateMovie(movie)
}