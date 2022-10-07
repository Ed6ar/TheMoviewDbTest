package com.vum.themoviedbapptest.data.models

sealed class Destinations(
    val name: String,
    val route: String
){
    object TopRated: Destinations(name = "topRated", route = "topRated")
    object MovieDetail: Destinations(name = "movieDetail", route = "movieDetail/{movieId}")
}
