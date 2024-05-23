package com.example.hoopla.domain.model

data class Content(
    val artKey: String,
    val children: Boolean,
    val deleted: Boolean,
    val demo: Boolean,
    val externalBingePass: Boolean,
    val fixedLayout: Boolean,
    val id: Int,
    val manga: Boolean,
    val mediaKey: String,
    val pa: Boolean,
    val playable: Boolean,
    val profanity: Boolean,
    val readAlong: Boolean,
    val sapId: String,
    val seconds: Int,
    val subtitles: List<Subtitle>,
    val synopsis: String,
    val title: String,
    val traditionalManga: Boolean
)