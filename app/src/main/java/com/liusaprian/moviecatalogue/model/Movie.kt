package com.liusaprian.moviecatalogue.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "movies")
@Parcelize
data class Movie (
    @PrimaryKey
    var id: Int,
    var backdrop: String?,
    var poster: String,
    var title: String,
    var overview: String,
    var date: String?,
    var rating: Double,
    var genres: String,
    var isFavorite: Boolean,
    var isMovie: Boolean
) : Parcelable