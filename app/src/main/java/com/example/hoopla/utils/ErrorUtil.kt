package com.example.hoopla.utils

import com.example.hoopla.domain.model.ErrorResponse
import retrofit2.Response
import retrofit2.Retrofit
import java.io.IOException

object ErrorUtil {

    fun parseError(response: Response<*>, retrofit: Retrofit): ErrorResponse? {
        val parser = retrofit.responseBodyConverter<ErrorResponse>(
            ErrorResponse::class.java,
            arrayOfNulls(0)
        )
        return try {
            response.errorBody()?.let { parser.convert(it) }
        } catch (e: IOException) {
            ErrorResponse()
        }
    }
}