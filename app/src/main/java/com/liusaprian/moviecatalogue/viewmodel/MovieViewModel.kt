package com.liusaprian.moviecatalogue.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.liusaprian.moviecatalogue.model.Movie
import com.liusaprian.moviecatalogue.data.MovieRepository
import com.liusaprian.moviecatalogue.vo.Resource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieViewModel @Inject constructor(private val repository: MovieRepository) : ViewModel() {

    fun getMovies(sort: String): LiveData<Resource<PagedList<Movie>>> = repository.getMovies(sort)

    fun getTvShows(sort: String): LiveData<Resource<PagedList<Movie>>> = repository.getTvShows(sort)

    fun getFavoriteMovies(): LiveData<PagedList<Movie>> = repository.getFavoriteMovies()

    fun getFavoriteTvShows(): LiveData<PagedList<Movie>> = repository.getFavoriteTvShows()

    fun switchFavoriteState(movie: Movie) {
        movie.isFavorite = !movie.isFavorite
        repository.switchFavoriteState(movie)
    }
}