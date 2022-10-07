package com.vum.themoviedbapptest.data.models.responses.getMovieDetail

data class ResultMovieDetail(
    val adult: Boolean,
    val backdrop_path: String,
    val belongs_to_collection: BelongsToCollection,
    val budget: Number,
    val genres: List<Genre>,
    val homepage: String,
    val id: Int,
    val imdb_id: String,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: String,
    val poster_path: String,
    val production_companies: List<ProductionCompanie>,
    val production_countries: List<ProductionCountries>,
    val release_date: String,
    val revenue: String,
    val runtime: Int,
    val spoken_languages: List<SpokenLanguages>,
    val status: String,
    val tagline: String,
    val title: String,
    val video: String,
    val vote_average: Float,
    val vote_count: Number
)
