package com.liusaprian.moviecatalogue.network

import com.liusaprian.moviecatalogue.model.responses.GenreResponse
import com.liusaprian.moviecatalogue.model.responses.MovieResponse
import com.liusaprian.moviecatalogue.model.responses.TvShowResponse
import com.liusaprian.moviecatalogue.utils.ResponseConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular")
    suspend fun getMoviePopularList(
            @Query("api_key") apiKey: String,
            @Query("language") language: String,
            @Query("page") page: Int
    ) : MovieResponse

    @GET("tv/popular")
    suspend fun getTvShowPopularList(
            @Query("api_key") apiKey: String,
            @Query("language") language: String,
            @Query("page") page: Int
    ) : TvShowResponse

    @GET("genre/{category}/list")
    suspend fun getGenres(
            @Path("category") category: String,
            @Query("api_key") apiKey: String,
            @Query("language") language: String
    ) : GenreResponse
}

val api: ApiService by lazy {
    Retrofit.Builder()
        .baseUrl(ResponseConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiService::class.java)
}