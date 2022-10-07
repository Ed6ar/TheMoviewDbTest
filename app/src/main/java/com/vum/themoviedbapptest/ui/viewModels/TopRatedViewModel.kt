package com.vum.themoviedbapptest.ui.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vum.themoviedbapptest.core.API_KEY
import com.vum.themoviedbapptest.data.models.Resource
import com.vum.themoviedbapptest.data.models.responses.getTopRated.ResultTopRated
import com.vum.themoviedbapptest.domine.GetTopRatedUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit

class TopRatedViewModel(
    private val retrofit: Retrofit
): ViewModel() {

    private var _topRatedList = MutableStateFlow<List<ResultTopRated>?>(null)
    var topRatedList = _topRatedList.asStateFlow()

    internal fun tryToGetTopRete(){
        viewModelScope.launch(Dispatchers.IO) {

            when(val resource = GetTopRatedUseCase(retrofit).execute(apiKey = API_KEY)){
                is Resource.Success -> {
                    _topRatedList.value = resource.data!!.results
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