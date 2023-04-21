package com.ad.moviesapp.api

import com.ad.moviesapp.response.MovieDeatilsResponse
import com.ad.moviesapp.response.MoviesListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular")
    fun getPopularMovies(@Query("page")page:Int):Call<MoviesListResponse>
    @GET("movie/{movie_id}")
    fun getMoviedeatalies(@Path("movie_id")id:Int):Call<MovieDeatilsResponse>
}