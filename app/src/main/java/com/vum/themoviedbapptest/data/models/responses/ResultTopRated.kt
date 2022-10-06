package com.vum.themoviedbapptest.data.models.responses

data class ResultTopRated(
    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: List<String>,
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Float,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: String,
    val vote_count: String
)
