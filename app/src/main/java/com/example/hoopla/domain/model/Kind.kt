package com.example.hoopla.domain.model

data class Kind(
    val enabled: Boolean,
    val id: Int,
    val name: String,
    val plural: String,
    val singular: String
)