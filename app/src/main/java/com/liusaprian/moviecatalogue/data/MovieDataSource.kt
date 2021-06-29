package com.liusaprian.moviecatalogue.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.liusaprian.moviecatalogue.model.Movie
import com.liusaprian.moviecatalogue.vo.Resource

interface MovieDataSource {
    fun getMovies(sort: String): LiveData<Resource<PagedList<Movie>>>

    fun getTvShows(sort: String): LiveData<Resource<PagedList<Movie>>>

    fun getFavoriteMovies(): LiveData<PagedList<Movie>>

    fun getFavoriteTvShows(): LiveData<PagedList<Movie>>

    fun switchFavoriteState(movie: Movie)
}