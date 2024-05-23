package com.example.hoopla.domain.model

data class ErrorResponse(
    val errorCode: Int = 0,
    val errorMessage: String? = null
)