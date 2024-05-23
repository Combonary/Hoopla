package com.example.hoopla.domain.model

data class GenreTree(
    val id: Int,
    val name: String,
    val subgenres: List<Any>
)