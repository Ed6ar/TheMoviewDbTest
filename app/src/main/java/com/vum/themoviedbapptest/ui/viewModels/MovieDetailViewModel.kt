package com.vum.themoviedbapptest.ui.viewModels

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
) : ViewModel() {

    private var _movieDetail = MutableStateFlow<ResultMovieDetail?>(null)
    var movieDetail = _movieDetail.asStateFlow()

    private var _showError = MutableStateFlow<Pair<Int?, String?>?>(null)
    var showError = _showError.asStateFlow()

    private var _showLoading = MutableStateFlow(true)
    var showLoading = _showLoading.asStateFlow()

    internal fun tryToGetMovieDetail(movieId: Int) {
        setLoadingState(true)
        viewModelScope.launch(Dispatchers.IO) {
            val resource = GetMovieDetailUseCase(retrofit = retrofit)
                .execute(
                    apiKey = API_KEY,
                    movieId = movieId
                )
            when (resource) {
                is Resource.Success -> {
                    _movieDetail.value = resource.data
                    setLoadingState(false)
                }
                is Resource.Error -> {
                    showError(Pair(null, resource.message))
                    setLoadingState(false)
                }
                is Resource.Exception -> {
                    showError(Pair(resource.int, null))
                    setLoadingState(false)
                }
            }
        }
    }

    private fun setLoadingState(state: Boolean) {
        _showLoading.value = state
    }

    private fun showError(value: Pair<Int?, String?>?) {
        _showError.value = value
    }

}