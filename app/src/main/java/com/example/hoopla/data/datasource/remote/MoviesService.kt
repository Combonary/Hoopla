package com.example.hoopla.data.datasource.remote

import com.example.hoopla.domain.model.MovieDetail
import com.example.hoopla.domain.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * rest api definitions for movies service
 */
interface MoviesService {

    @GET("/kinds/7/titles/top")
    suspend fun getMoviesList(): Response<MovieResponse>

    @GET("/titles/{titleId}")
    suspend fun getMovie(@Path("titleId") id: Int): Response<MovieDetail>
}