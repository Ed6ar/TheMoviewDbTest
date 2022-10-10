package com.vum.themoviedbapptest.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vum.themoviedbapptest.core.API_KEY
import com.vum.themoviedbapptest.data.models.BasicOperations
import com.vum.themoviedbapptest.data.models.Resource
import com.vum.themoviedbapptest.data.models.responses.getTopRated.ResultTopRated
import com.vum.themoviedbapptest.domine.GetTopRatedUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit

class TopRatedViewModel(
    private val retrofit: Retrofit
) : ViewModel(), BasicOperations {

    private var _topRatedList = MutableStateFlow<List<ResultTopRated>?>(null)
    var topRatedList = _topRatedList.asStateFlow()

    private var _showError = MutableStateFlow<Pair<Int?, String?>?>(null)
    override var showError = _showError.asStateFlow()

    private var _internetError = MutableStateFlow(false)
    override var internetError = _internetError.asStateFlow()

    private var _showLoading = MutableStateFlow(true)
    override var showLoading = _showLoading.asStateFlow()

    override val coroutineExceptionHandler = CoroutineExceptionHandler { _, _ ->
        setLoadingState(false)
        _internetError.value = true
    }

    internal fun tryToGetTopRete() {
        viewModelScope.launch(coroutineExceptionHandler) {

            when (val resource = GetTopRatedUseCase(retrofit).execute(apiKey = API_KEY)) {
                is Resource.Success -> {
                    _topRatedList.value = resource.data!!.results
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

    override fun setLoadingState(state: Boolean) {
        _showLoading.value = state
    }

    override fun showError(value: Pair<Int?, String?>?) {
        _showError.value = value
    }

}