package com.vum.themoviedbapptest.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vum.themoviedbapptest.data.models.responses.ResultTopRated
import com.vum.themoviedbapptest.ui.theme.TheMovieDBAppTestTheme
import coil.compose.rememberImagePainter

private const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"

@Preview
@Composable
private fun ShowTopRatedCardPreview(){
    TheMovieDBAppTestTheme {
        //TopRatedCardPreview(ResultTopRated())
    }
}

@Composable
fun TopRatedCardPreview(resultTopRated: ResultTopRated, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .wrapContentSize()
            .clickable {
                onClick()
            },
        elevation = 3.dp
    ) {
        Column(
            modifier = Modifier.wrapContentSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = rememberImagePainter(
                    data = IMAGE_BASE_URL.plus(resultTopRated.poster_path),
                    builder = {
                        crossfade(true)
                    }
                ),
                contentDescription = "Movie image",
                modifier = Modifier
                    .height(200.dp)
                    .width(150.dp),
                contentScale = ContentScale.FillBounds
            )

            Text(
                text = resultTopRated.title,
                color = MaterialTheme.colors.primary,
                fontSize = 16.sp
            )
        }

    }
}