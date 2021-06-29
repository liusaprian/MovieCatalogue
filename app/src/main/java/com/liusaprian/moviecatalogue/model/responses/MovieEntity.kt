package com.liusaprian.moviecatalogue.model.responses

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieEntity(
    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("backdrop_path")
    val backdrop: String,

    @field:SerializedName("poster_path")
    val poster: String,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("overview")
    val overview: String,

    @field:SerializedName("release_date")
    val releaseDate: String,

    @field:SerializedName("vote_average")
    val rating: Double,

    @field:SerializedName("genre_ids")
    val genres: ArrayList<Int>,
) : Parcelable

data class MovieResponse(
    @field:SerializedName("results")
    val movies: List<MovieEntity>
)