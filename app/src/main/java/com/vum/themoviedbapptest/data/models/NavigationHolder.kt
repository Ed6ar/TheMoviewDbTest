package com.vum.themoviedbapptest.data.models

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

object NavigationHolder {

    private val mainSharedFlow = MutableSharedFlow<NavigationParameters>()
    val sharedFlow = mainSharedFlow.asSharedFlow()

    fun navigateTo(route: String){
        CoroutineScope(Dispatchers.IO).launch {
            mainSharedFlow.emit(
                NavigationParameters(
                    route = route
                )
            )
        }
    }
}