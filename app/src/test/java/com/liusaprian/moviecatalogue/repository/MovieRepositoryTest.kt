package com.liusaprian.moviecatalogue.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.DataSource
import com.liusaprian.moviecatalogue.data.local.LocalDataSource
import com.liusaprian.moviecatalogue.data.remote.RemoteDataSource
import com.liusaprian.moviecatalogue.model.Movie
import com.liusaprian.moviecatalogue.utils.AppExecutors
import com.liusaprian.moviecatalogue.utils.DataDummy
import com.liusaprian.moviecatalogue.utils.PagedListUtil
import com.liusaprian.moviecatalogue.vo.Resource
import org.junit.Test
import org.junit.Assert.*
import org.junit.Rule
import org.mockito.Mockito.*

class MovieRepositoryTest {

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)
    private val movieRepository = FakeMovieRepository(remote, local, appExecutors)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()


    @Test
    fun getMovies() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, Movie>
        `when`(local.getMovies("")).thenReturn(dataSourceFactory)
        movieRepository.getMovies("")

        val movies = Resource.success(PagedListUtil.mockPagedList(DataDummy.getMovies()))
        verify(local).getMovies("")
        assertNotNull(movies.data)
        assertEquals(4, movies.data?.size)
    }

    @Test
    fun getTvShows() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, Movie>
        `when`(local.getTvShows("")).thenReturn(dataSourceFactory)
        movieRepository.getTvShows("")

        val tvShows = Resource.success(PagedListUtil.mockPagedList(DataDummy.getTvShows()))
        verify(local).getTvShows("")
        assertNotNull(tvShows.data)
        assertEquals(4, tvShows.data?.size)
    }

    @Test
    fun getFavoriteMovies() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, Movie>
        `when`(local.getFavoriteMovies()).thenReturn(dataSourceFactory)
        movieRepository.getFavoriteMovies()

        val favMovies = PagedListUtil.mockPagedList(DataDummy.getFavoriteMovies())
        verify(local).getFavoriteMovies()
        assertNotNull(favMovies)
        assertEquals(DataDummy.getFavoriteMovies().size, favMovies.size)
    }

    @Test
    fun getFavoriteTvShows() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, Movie>
        `when`(local.getFavoriteTvShows()).thenReturn(dataSourceFactory)
        movieRepository.getFavoriteTvShows()

        val favTvShows = PagedListUtil.mockPagedList(DataDummy.getFavoriteTvShows())
        verify(local).getFavoriteTvShows()
        assertNotNull(favTvShows)
        assertEquals(DataDummy.getFavoriteTvShows().size, favTvShows.size)
    }

    @Test
    fun switchFavoriteStateFromTrueToFalse() {
        val movie = DataDummy.getFavoriteMovie()
        assertEquals(true, movie.isFavorite)

        `when`(local.switchFavoriteState(movie)).thenAnswer {
            val movieArg = it.arguments[0] as Movie
            movieArg.isFavorite = !movieArg.isFavorite

            null
        }

        movieRepository.switchFavoriteState(movie)
        verify(local).switchFavoriteState(movie)

        assertEquals(false, movie.isFavorite)
    }

}