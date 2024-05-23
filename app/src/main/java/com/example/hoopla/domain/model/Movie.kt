package com.example.hoopla.domain.model

data class Movie(
    val artKey: String,
    val artistName: String,
    val children: Boolean,
    val demo: Boolean,
    val edited: Boolean,
    val fixedLayout: Boolean,
    val kind: String,
    val kindName: String,
    val pa: Boolean,
    val readAlong: Boolean,
    val title: String,
    val titleId: Int,
    val traditionalManga: Boolean,
    val year: Int
)