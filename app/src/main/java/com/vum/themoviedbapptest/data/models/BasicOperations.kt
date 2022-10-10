package com.vum.themoviedbapptest.data.models

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.StateFlow

interface BasicOperations {
    var showError: StateFlow<Pair<Int?, String?>?>
    var internetError: StateFlow<Boolean>
    var showLoading: StateFlow<Boolean>
    val coroutineExceptionHandler: CoroutineExceptionHandler

    fun setLoadingState(state:Boolean)
    fun showError(value: Pair<Int?, String?>?)
}