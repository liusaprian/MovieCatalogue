package com.liusaprian.moviecatalogue.utils

import androidx.sqlite.db.SimpleSQLiteQuery
import java.lang.StringBuilder

object SortUtils {
    const val ASCENDING_RATING = "asc"
    const val DESCENDING_RATING = "desc"

    fun getSortedQuery(category: String, filter: String): SimpleSQLiteQuery {
        val isMovie = if(category == ResponseConfig.MOVIE) 1 else 0
        val simpleSQLiteQuery = StringBuilder().append("SELECT * FROM movies WHERE isMovie = $isMovie")
        if(filter == ASCENDING_RATING) simpleSQLiteQuery.append(" ORDER BY rating ASC")
        else if(filter == DESCENDING_RATING) simpleSQLiteQuery.append(" ORDER BY rating DESC")
        return SimpleSQLiteQuery(simpleSQLiteQuery.toString())
    }
}