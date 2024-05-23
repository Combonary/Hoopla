package com.example.hoopla.data.repository

import com.example.hoopla.data.datasource.remote.MoviesApi
import com.example.hoopla.domain.model.MovieDetail
import com.example.hoopla.domain.model.MovieResponse
import com.example.hoopla.domain.model.ServerResult
import com.example.hoopla.domain.repository.MoviesRepository
import javax.inject.Inject

/**
 * repository class to fetch movie related data
 */
class MoviesRepositoryImpl @Inject constructor(
    private val moviesApi: MoviesApi
) : MoviesRepository {

    /**
     * returns a list of top movies
     */
    override suspend fun getMovies(): ServerResult<MovieResponse> {
        return moviesApi.getMoviesList()
    }

    /**
     * returns the details of a movie with the specified id
     * @param id titleId of movie
     */
    override suspend fun getMovieDetail(id: Int): ServerResult<MovieDetail> {
        return moviesApi.getMovie(id)
    }
}