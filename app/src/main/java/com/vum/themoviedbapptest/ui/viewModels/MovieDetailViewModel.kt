package com.vum.themoviedbapptest.ui.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vum.themoviedbapptest.core.API_KEY
import com.vum.themoviedbapptest.data.models.Resource
import com.vum.themoviedbapptest.data.models.responses.getMovieDetail.ResultMovieDetail
import com.vum.themoviedbapptest.domine.GetMovieDetailUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit

class MovieDetailViewModel(
    private val retrofit: Retrofit
): ViewModel() {

    private var _movieDetail = MutableStateFlow<ResultMovieDetail?>(null)
    var movieDetail = _movieDetail.asStateFlow()

    internal fun tryToGetMovieDetail(movieId: Int){
        viewModelScope.launch(Dispatchers.IO) {
            val resource = GetMovieDetailUseCase(retrofit = retrofit)
                .execute(
                    apiKey = API_KEY,
                    movieId = movieId
                )
            when(resource){
                is Resource.Success -> {
                    _movieDetail.value = resource.data
                }
                is Resource.Error -> {
                    Log.e("LIST_OF","Error: ${resource.message}")
                }
                is Resource.Exception -> {
                    Log.e("LIST_OF","Exception: ${resource.int}")
                }
            }
        }
    }

}