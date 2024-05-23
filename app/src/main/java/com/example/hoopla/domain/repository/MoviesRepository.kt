package com.example.hoopla.domain.repository

import com.example.hoopla.domain.model.MovieDetail
import com.example.hoopla.domain.model.MovieResponse
import com.example.hoopla.domain.model.ServerResult

interface MoviesRepository {

    suspend fun getMovies(): ServerResult<MovieResponse>

    suspend fun getMovieDetail(id: Int): ServerResult<MovieDetail>
}