package com.example.hoopla.domain.model

data class Subtitle(
    val cc: Boolean,
    val format: String,
    val id: Int,
    val iso639: String,
    val language: String,
    val languageId: Int,
    val mediaKey: String,
    val url: String
)