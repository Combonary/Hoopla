package com.example.hoopla.data.datasource.remote

import com.example.hoopla.domain.model.MovieDetail
import com.example.hoopla.domain.model.MovieResponse
import com.example.hoopla.domain.model.ServerResult
import com.example.hoopla.utils.ErrorUtil
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

/**
 * movies api class to interact with webservice
 */
class MoviesApi @Inject constructor(
    private val retrofit: Retrofit,
    private val moviesService: MoviesService
) {
    /**
     * parses server response
     * @param request request sent to server
     * @param errorMessage error message for debugging purposes
     */
    private suspend fun <T> getResponse(
        request: suspend () -> Response<T>,
        errorMessage: String
    ): ServerResult<T> {
        return try {
            val result = request.invoke()
            if (result.isSuccessful) {
                return ServerResult.success(result.body())
            } else {
                val errorResponse = ErrorUtil.parseError(result, retrofit)
                ServerResult.error(errorResponse?.errorMessage ?: errorMessage, errorResponse)
            }
        } catch (e: Throwable) {
            ServerResult.error(e.message ?: "Unknown Error", null)
        }
    }

    /**
     * returns a server result with a list of movies or an empty list when an error occurs
     */
    suspend fun getMoviesList(): ServerResult<MovieResponse> {
        return getResponse(
            request = { moviesService.getMoviesList() },
            errorMessage = "error occurred when getting movies list"
        )
    }

    suspend fun getMovie(id: Int): ServerResult<MovieDetail> {
        return getResponse(
            request = { moviesService.getMovie(id) },
            errorMessage = "error occurred when getting movie detail"
        )
    }
}