package com.vum.themoviedbapptest.ui.views.commonElements

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vum.themoviedbapptest.R
import com.vum.themoviedbapptest.ui.theme.TheMovieDBAppTestTheme

@Preview
@Composable
private fun ShowNotInternetConnection(){
    TheMovieDBAppTestTheme {
        NotInternetConnection()
    }
}

@Composable
fun NotInternetConnection(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_baseline_signal_wifi_connected_no_internet_4_24),
            contentDescription = "Not internet connection",
            modifier = Modifier
                .size(250.dp)
                .padding(15.dp)
        )
        Text(
            text = stringResource(id = R.string.internetException),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 16.sp
        )
    }
}