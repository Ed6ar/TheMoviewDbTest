package com.vum.themoviedbapptest.core

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.vum.themoviedbapptest.data.di.networkModule
import com.vum.themoviedbapptest.data.di.viewModels
import com.vum.themoviedbapptest.data.models.NavigationHolder
import com.vum.themoviedbapptest.ui.NavControllerCustom
import com.vum.themoviedbapptest.ui.theme.TheMovieDBAppTestTheme
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"
const val API_KEY = "22cc8e67a171ee39299cb4af19ba9853"

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startKoin {
            modules(
                networkModule,
                viewModels
            )
        }

        setContent {

            val navController = rememberNavController()

            TheMovieDBAppTestTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background,
                    content = {
                        NavControllerCustom(navController = navController)
                    }
                )
            }

            //Navigation reference
            LaunchedEffect(key1 = "navigation") {
                NavigationHolder.sharedFlow.onEach {
                    navController.navigate(route = it.route)
                }.launchIn(this)
            }
        }
    }

    override fun onStop() {
        super.onStop()

        stopKoin()
    }
}