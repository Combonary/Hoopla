package com.example.hoopla.domain.model

data class MovieDetail(
    val abridged: Boolean,
    val artKey: String,
    val artist: Artist,
    val artists: List<ArtistX>,
    val children: Boolean,
    val contents: List<Content>,
    val copyright: String,
    val deleted: Boolean,
    val demo: Boolean,
    val displayDate: Long,
    val edited: Boolean,
    val externalBingePass: Boolean,
    val favorite: Boolean,
    val fixedLayout: Boolean,
    val genrePaths: List<List<Int>>,
    val genreTrees: List<GenreTree>,
    val genres: List<Genre>,
    val id: Int,
    val internalBingePass: Boolean,
    val kind: Kind,
    val language: Language,
    val lendingMessage: String,
    val licenseLevel: String,
    val pa: Boolean,
    val playable: Boolean,
    val profanity: Boolean,
    val publisher: Publisher,
    val rating: String,
    val readAlong: Boolean,
    val related: List<Any>,
    val releaseDate: Long,
    val reviews: List<Any>,
    val synopsis: String,
    val tags: List<Any>,
    val title: String,
    val traditionalManga: Boolean,
    val year: Int
)