package com.example.hoopla.domain.model

data class Genre(
    val bisac: String,
    val hasChildren: Boolean,
    val id: Int,
    val kindId: Int,
    val name: String
)