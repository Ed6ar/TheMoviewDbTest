package com.vum.themoviedbapptest.ui.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vum.themoviedbapptest.ui.theme.TheMovieDBAppTestTheme
import com.vum.themoviedbapptest.ui.viewModels.TopRatedViewModel
import org.koin.androidx.compose.getViewModel

@Preview
@Composable
private fun ShowTopRatedPreview(){
    TheMovieDBAppTestTheme{
        TopRatedView(
            viewModel = getViewModel(),
            onClick = {}
        )
    }
}

@Composable
fun TopRatedView(
    viewModel: TopRatedViewModel = getViewModel(),
    onClick: (movieId: Int) -> Unit
) {

    LaunchedEffect(key1 = "init"){
        viewModel.tryToGetTopRete()
    }

    val topRatedList by viewModel.topRatedList.collectAsState()

    if(topRatedList != null){
        LazyRow(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 15.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            content = {
                items(
                    items = topRatedList!!,
                    itemContent = { item ->
                        TopRatedCardPreview(
                            resultTopRated = item,
                            onClick = onClick
                        )
                    }
                )
            }
        )
    }else{
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .height(150.dp)
                    .width(150.dp)
            )
        }
    }

}