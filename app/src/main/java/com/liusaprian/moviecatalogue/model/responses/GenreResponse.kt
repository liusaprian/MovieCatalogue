package com.liusaprian.moviecatalogue.model.responses

import com.google.gson.annotations.SerializedName

data class GenreResponse(
    @field:SerializedName("genres")
    val genres: List<GenreItem>
)

data class GenreItem(
    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("name")
    val genreName: String
)