package com.liusaprian.moviecatalogue.repository

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.liusaprian.moviecatalogue.data.MovieDataSource
import com.liusaprian.moviecatalogue.data.NetworkBoundResource
import com.liusaprian.moviecatalogue.data.local.LocalDataSource
import com.liusaprian.moviecatalogue.data.remote.RemoteDataSource
import com.liusaprian.moviecatalogue.model.Movie
import com.liusaprian.moviecatalogue.network.ApiResponse
import com.liusaprian.moviecatalogue.utils.AppExecutors
import com.liusaprian.moviecatalogue.vo.Resource

class FakeMovieRepository constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : MovieDataSource {

    override fun getMovies(sort: String): LiveData<Resource<PagedList<Movie>>> {
        return object : NetworkBoundResource<PagedList<Movie>, List<Movie>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<Movie>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(10)
                    .setPageSize(5)
                    .build()
                return LivePagedListBuilder(localDataSource.getMovies(sort), config).build()
            }

            override fun shouldFetch(data: PagedList<Movie>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<Movie>>> =
                remoteDataSource.getPopularMovies()

            override fun saveCallResult(data: List<Movie>) =
                localDataSource.insertMovies(data)

        }.asLiveData()
    }

    override fun getTvShows(sort: String): LiveData<Resource<PagedList<Movie>>> {
        return object : NetworkBoundResource<PagedList<Movie>, List<Movie>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<Movie>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(10)
                    .setPageSize(5)
                    .build()
                return LivePagedListBuilder(localDataSource.getTvShows(sort), config).build()
            }

            override fun shouldFetch(data: PagedList<Movie>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<Movie>>> =
                remoteDataSource.getPopularTvShows()

            override fun saveCallResult(data: List<Movie>) =
                localDataSource.insertMovies(data)

        }.asLiveData()
    }

    override fun getFavoriteMovies(): LiveData<PagedList<Movie>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(10)
            .setPageSize(5)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoriteMovies(), config).build()
    }

    override fun getFavoriteTvShows(): LiveData<PagedList<Movie>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(10)
            .setPageSize(5)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoriteTvShows(), config).build()

    }

    override fun switchFavoriteState(movie: Movie) =
        localDataSource.switchFavoriteState(movie)
}