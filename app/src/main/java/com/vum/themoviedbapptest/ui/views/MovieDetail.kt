package com.vum.themoviedbapptest.ui.views

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.vum.themoviedbapptest.R
import com.vum.themoviedbapptest.core.IMAGE_BASE_URL
import com.vum.themoviedbapptest.ui.theme.TheMovieDBAppTestTheme
import com.vum.themoviedbapptest.ui.viewModels.MovieDetailViewModel
import org.koin.androidx.compose.getViewModel

@Preview
@Composable
private fun ShowMovieDetailPreview() {
    TheMovieDBAppTestTheme {
        MovieDetail(movieId = 123)
    }
}

@Composable
fun MovieDetail(
    movieId: Int,
    viewModel: MovieDetailViewModel = getViewModel()
) {

    val movieDetail by viewModel.movieDetail.collectAsState()
    val showLoading by viewModel.showLoading.collectAsState()
    val showError by viewModel.showError.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(key1 = "init") {
        viewModel.tryToGetMovieDetail(movieId = movieId)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(15.dp),
        horizontalAlignment = Alignment.Start
    ) {
        if (showLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .height(150.dp)
                    .width(150.dp)
            )
        } else {
            movieDetail?.let {
                Image(
                    painter = rememberImagePainter(
                        data = IMAGE_BASE_URL.plus(movieDetail!!.poster_path),
                        builder = {
                            crossfade(true)
                        }
                    ),
                    contentDescription = "Movie image",
                    modifier = Modifier
                        .height(300.dp)
                        .fillMaxWidth(),
                    contentScale = ContentScale.FillBounds
                )

                Text(
                    text = stringResource(id = R.string.title),
                    modifier = Modifier.padding(start = 20.dp),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = movieDetail!!.title,
                    modifier = Modifier.padding(start = 20.dp),
                    fontSize = 18.sp,
                )

                Text(
                    text = stringResource(id = R.string.releaseDate),
                    modifier = Modifier.padding(start = 20.dp),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = movieDetail!!.release_date,
                    modifier = Modifier.padding(start = 20.dp),
                    fontSize = 18.sp
                )
            }
        }
    }

    val errorMessage = when {
        showError?.first != null -> stringResource(id = showError!!.first!!)
        showError?.second != null -> showError!!.second
        else -> ""
    }

    LaunchedEffect(key1 = errorMessage) {
        if (errorMessage!!.isNotEmpty()) {
            Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
        }
    }

}