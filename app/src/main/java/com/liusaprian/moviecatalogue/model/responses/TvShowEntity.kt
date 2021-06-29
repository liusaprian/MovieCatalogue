package com.liusaprian.moviecatalogue.model.responses

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class TvShowEntity(
	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("backdrop_path")
	val backdrop: String,

	@field:SerializedName("poster_path")
	val poster: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("overview")
	val overview: String,

	@field:SerializedName("first_air_date")
	val firstAirDate: String,

	@field:SerializedName("vote_average")
	val rating: Double,

	@field:SerializedName("genre_ids")
	val genres: ArrayList<Int>,
) : Parcelable

data class TvShowResponse(
	@field:SerializedName("results")
	val tvShows: List<TvShowEntity>
)