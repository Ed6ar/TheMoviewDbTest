package com.vum.themoviedbapptest.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vum.themoviedbapptest.data.models.responses.getTopRated.ResultTopRated
import com.vum.themoviedbapptest.ui.theme.TheMovieDBAppTestTheme
import coil.compose.rememberImagePainter
import com.vum.themoviedbapptest.R
import com.vum.themoviedbapptest.core.IMAGE_BASE_URL

@Preview
@Composable
private fun ShowTopRatedCardPreview(){
    TheMovieDBAppTestTheme {
        //TopRatedCardPreview(ResultTopRated())
    }
}

@Composable
fun TopRatedCardPreview(
    resultTopRated: ResultTopRated,
    onClick: (movieId: Int) -> Unit)
{

    Card(
        modifier = Modifier
            .wrapContentHeight()
            .width(160.dp)
            .clickable {
                onClick(resultTopRated.id)
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
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(horizontal = 10.dp),
                color = MaterialTheme.colors.primary,
                fontSize = 16.sp
            )
            
            Row(
                modifier = Modifier.fillMaxWidth().padding(start = 15.dp, top = 10.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_stars_24), 
                    contentDescription = "Rate",
                    modifier = Modifier.size(30.dp)
                )
                Text(
                    text = resultTopRated.vote_average,
                    fontSize = 16.sp
                )
            }
        }

    }
}