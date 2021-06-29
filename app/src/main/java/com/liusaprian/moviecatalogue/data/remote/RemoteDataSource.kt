package com.liusaprian.moviecatalogue.data.remote

import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.liusaprian.moviecatalogue.model.Movie
import com.liusaprian.moviecatalogue.model.responses.GenreItem
import com.liusaprian.moviecatalogue.network.ApiResponse
import com.liusaprian.moviecatalogue.network.api
import com.liusaprian.moviecatalogue.utils.EspressoIdlingResource
import com.liusaprian.moviecatalogue.utils.ResponseConfig.Companion.API_KEY
import com.liusaprian.moviecatalogue.utils.ResponseConfig.Companion.LANGUAGE
import com.liusaprian.moviecatalogue.utils.ResponseConfig.Companion.PAGE
import com.liusaprian.moviecatalogue.utils.ResponseConfig.Companion.MOVIE
import com.liusaprian.moviecatalogue.utils.ResponseConfig.Companion.TV
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor() {

    private val service = api

    fun getPopularMovies(): LiveData<ApiResponse<List<Movie>>> {
        EspressoIdlingResource.increment()
        val result = MutableLiveData<ApiResponse<List<Movie>>>()
        val movieGenres = getGenres(MOVIE)
        CoroutineScope(Dispatchers.IO).launch {
            val response = service.getMoviePopularList(API_KEY, LANGUAGE, PAGE).movies
            val movieList: List<Movie> = response.map {
                val movieGenreList = ArrayList<String>()
                for (genreId in it.genres)
                    for (genre in movieGenres)
                        if (genreId == genre.id) {
                            movieGenreList.add(genre.genreName)
                            break
                        }

                val genreString = TextUtils.join(", ", movieGenreList)

                Movie(
                    it.id,
                    it.backdrop,
                    it.poster,
                    it.title,
                    it.overview,
                    it.releaseDate,
                    it.rating,
                    genreString,
                    isFavorite = false,
                    isMovie = true
                )
            }

            result.postValue(ApiResponse.success(movieList))
        }
        EspressoIdlingResource.decrement()
        return result
    }

    fun getPopularTvShows(): LiveData<ApiResponse<List<Movie>>> {
        EspressoIdlingResource.increment()
        val result = MutableLiveData<ApiResponse<List<Movie>>>()
        val tvShowGenres = getGenres(TV)
        CoroutineScope(Dispatchers.IO).launch {
            val response = service.getTvShowPopularList(API_KEY, LANGUAGE, PAGE).tvShows
            val tvShowList: List<Movie> = response.map {
                val tvShowGenreList = ArrayList<String>()
                for (genreId in it.genres)
                    for (genre in tvShowGenres)
                        if (genreId == genre.id) {
                            tvShowGenreList.add(genre.genreName)
                            break
                        }

                val genreString = TextUtils.join(", ", tvShowGenreList)

                Movie(
                    it.id,
                    it.backdrop,
                    it.poster,
                    it.name,
                    it.overview,
                    it.firstAirDate,
                    it.rating,
                    genreString,
                    isFavorite = false,
                    isMovie = false
                )
            }

            result.postValue(ApiResponse.success(tvShowList))
        }
        EspressoIdlingResource.decrement()
        return result
    }

    private fun getGenres(category: String): List<GenreItem> {
        val result = ArrayList<GenreItem>()
        CoroutineScope(Dispatchers.IO).launch {
            result.addAll(service.getGenres(category, API_KEY, LANGUAGE).genres)
        }
        return result
    }
}