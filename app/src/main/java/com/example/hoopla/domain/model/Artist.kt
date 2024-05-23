package com.example.hoopla.domain.model

data class Artist(
    val artKey: String,
    val bio: String,
    val favorite: Boolean,
    val id: Int,
    val name: String,
    val similar: List<Any>
)