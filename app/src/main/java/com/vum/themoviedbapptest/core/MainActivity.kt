package com.vum.themoviedbapptest.core

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.vum.themoviedbapptest.data.di.BASE_URL
import com.vum.themoviedbapptest.data.di.networkModule
import com.vum.themoviedbapptest.data.di.viewModels
import com.vum.themoviedbapptest.ui.theme.TheMovieDBAppTestTheme
import com.vum.themoviedbapptest.ui.viewModels.TopRatedViewModel
import com.vum.themoviedbapptest.ui.views.TopRatedView
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.startKoin

class MainActivity : ComponentActivity() {

    private val topRatedViewModel: TopRatedViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startKoin {
            modules(
                networkModule,
                viewModels
            )
        }

        topRatedViewModel.tryToGetTopRete()

        setContent {

            val topRatedList by topRatedViewModel.topRatedList.collectAsState()

            TheMovieDBAppTestTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    TopRatedView(
                        topRatedList = topRatedList,
                        onClick = {
                            //TODO
                        }
                    )
                }
            }
        }
    }
}