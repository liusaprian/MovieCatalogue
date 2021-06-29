package com.liusaprian.moviecatalogue.utils

import com.liusaprian.moviecatalogue.model.Movie

object DataDummy {

    fun getFavoriteMovie(): Movie = Movie(1, "test", "test", "test", "test", "2020-02-20", 1.0, "Action", true, true)

    fun getMovies(): List<Movie> {
        return listOf(
            Movie(1, "test", "test", "test", "test", "2020-02-20", 1.0, "Action", false, true),
            Movie(2, "test", "test", "test", "test", "2020-02-20", 2.0, "Drama", false, true),
            Movie(3, "test", "test", "test", "test", "2020-02-20", 3.0, "Fantasy", false, true),
            Movie(4, "test", "test", "test", "test", "2020-02-20", 4.0, "Crime", false, true)
        )
    }

    fun getTvShows(): List<Movie> {
        return listOf(
            Movie(1, "test", "test", "test", "test", "2020-02-20", 1.0, "Action", false, false),
            Movie(2, "test", "test", "test", "test", "2020-02-20", 2.0, "Drama", false, false),
            Movie(3, "test", "test", "test", "test", "2020-02-20", 3.0, "Fantasy", false, false),
            Movie(4, "test", "test", "test", "test", "2020-02-20", 4.0, "Crime", false, false)
        )
    }

    fun getFavoriteMovies(): List<Movie> {
        return listOf(
            Movie(1, "test", "test", "test", "test", "2020-02-20", 1.0, "Action", true, true),
            Movie(2, "test", "test", "test", "test", "2020-02-20", 2.0, "Drama", true, true),
            Movie(3, "test", "test", "test", "test", "2020-02-20", 3.0, "Fantasy", true, true),
            Movie(4, "test", "test", "test", "test", "2020-02-20", 4.0, "Crime", true, true)
        )
    }

    fun getFavoriteTvShows(): List<Movie> {
        return listOf(
            Movie(1, "test", "test", "test", "test", "2020-02-20", 1.0, "Action", true, false),
            Movie(2, "test", "test", "test", "test", "2020-02-20", 2.0, "Drama", true, false),
            Movie(3, "test", "test", "test", "test", "2020-02-20", 3.0, "Fantasy", true, false),
            Movie(4, "test", "test", "test", "test", "2020-02-20", 4.0, "Crime", true, false)
        )
    }

}