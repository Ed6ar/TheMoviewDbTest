package com.vum.themoviedbapptest.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vum.themoviedbapptest.data.models.responses.ResultTopRated
import com.vum.themoviedbapptest.ui.theme.TheMovieDBAppTestTheme

@Preview
@Composable
private fun ShowTopRatedPreview(){
    TheMovieDBAppTestTheme{
        TopRatedView(listOf()){}
    }
}

@Composable
fun TopRatedView(topRatedList: List<ResultTopRated>, onClick: () -> Unit) {
    LazyRow(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(horizontal = 15.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        content = {
            items(
                items = topRatedList,
                itemContent = { item ->
                    TopRatedCardPreview(
                        resultTopRated = item,
                        onClick = onClick
                    )
                }
            )
        }
    )
}