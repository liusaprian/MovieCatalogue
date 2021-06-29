package com.liusaprian.moviecatalogue.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.liusaprian.moviecatalogue.data.MovieRepository
import com.liusaprian.moviecatalogue.model.Movie
import com.liusaprian.moviecatalogue.utils.DataDummy
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import com.liusaprian.moviecatalogue.vo.Resource

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var movieViewModel: MovieViewModel
    private lateinit var repository: MovieRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<Movie>>>

    @Mock
    private lateinit var favoriteObserver: Observer<PagedList<Movie>>

    @Mock
    private lateinit var pagedList: PagedList<Movie>

    @Before
    fun setup() {
        repository = mock(MovieRepository::class.java)
        movieViewModel = MovieViewModel(repository)
    }

    @Test
    fun `Get Movies`() {
        val dummyMovies = Resource.success(pagedList)
        `when`(dummyMovies.data?.size).thenReturn(4)
        val movies = MutableLiveData<Resource<PagedList<Movie>>>()
        movies.value = dummyMovies

        `when`(movieViewModel.getMovies("")).thenReturn(movies)
        val movieEntities = movieViewModel.getMovies("").value?.data
        verify(repository).getMovies("")
        assertNotNull(movieEntities)
        assertEquals(4, movieEntities?.size)

        movieViewModel.getMovies("").observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }

    @Test
    fun `Get Tv Shows`() {
        val dummyTvShows = Resource.success(pagedList)
        `when`(dummyTvShows.data?.size).thenReturn(4)
        val tvShows = MutableLiveData<Resource<PagedList<Movie>>>()
        tvShows.value = dummyTvShows

        `when`(movieViewModel.getTvShows("")).thenReturn(tvShows)
        val tvShowEntities = movieViewModel.getTvShows("").value?.data
        verify(repository).getTvShows("")
        assertNotNull(tvShowEntities)
        assertEquals(4, tvShowEntities?.size)

        movieViewModel.getTvShows("").observeForever(observer)
        verify(observer).onChanged(dummyTvShows)
    }

    @Test
    fun `Get Favorite Movies`() {
        val dummyFavMovies = pagedList
        `when`(dummyFavMovies.size).thenReturn(4)
        val favMovies = MutableLiveData<PagedList<Movie>>()
        favMovies.value = dummyFavMovies

        `when`(movieViewModel.getFavoriteMovies()).thenReturn(favMovies)
        val favMovieEntities = movieViewModel.getFavoriteMovies().value
        verify(repository).getFavoriteMovies()
        assertNotNull(favMovieEntities)
        assertEquals(4, favMovieEntities?.size)

        movieViewModel.getFavoriteMovies().observeForever(favoriteObserver)
        verify(favoriteObserver).onChanged(dummyFavMovies)
    }

    @Test
    fun `Get Favorite Tv Shows`() {
        val dummyFavTvShows = pagedList
        `when`(dummyFavTvShows.size).thenReturn(4)
        val favTvShows = MutableLiveData<PagedList<Movie>>()
        favTvShows.value = dummyFavTvShows

        `when`(movieViewModel.getFavoriteTvShows()).thenReturn(favTvShows)
        val favTvShowEntities = movieViewModel.getFavoriteTvShows().value
        verify(repository).getFavoriteTvShows()
        assertNotNull(favTvShowEntities)
        assertEquals(4, favTvShowEntities?.size)

        movieViewModel.getFavoriteTvShows().observeForever(favoriteObserver)
        verify(favoriteObserver).onChanged(dummyFavTvShows)
    }

    @Test
    fun `Switch Favorite State from True to False`() {
        val dummyMovie = DataDummy.getFavoriteMovie()
        assertEquals(true, dummyMovie.isFavorite)

        movieViewModel.switchFavoriteState(dummyMovie)
        verify(repository).switchFavoriteState(dummyMovie)

        assertEquals(false, dummyMovie.isFavorite)
    }
}