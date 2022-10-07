package com.vum.themoviedbapptest.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.vum.themoviedbapptest.data.models.Destinations
import com.vum.themoviedbapptest.data.models.NavigationHolder
import com.vum.themoviedbapptest.ui.views.MovieDetail
import com.vum.themoviedbapptest.ui.views.topRated.TopRatedView

@Composable
fun NavControllerCustom(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = Destinations.TopRated.route,
        modifier = Modifier.fillMaxSize()
    ) {
        composable(Destinations.TopRated.route) {
            TopRatedView(
                onClick = { movieId ->
                    NavigationHolder.navigateTo(
                        Destinations.MovieDetail.name.plus("/$movieId")
                    )
                }
            )
        }
        composable(Destinations.MovieDetail.route) {
            val movieId = it.arguments!!.getString("movieId")
            MovieDetail(
                movieId = movieId!!.toInt()
            )
        }

    }

}