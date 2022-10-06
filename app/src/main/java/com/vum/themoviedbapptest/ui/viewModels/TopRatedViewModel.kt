package com.vum.themoviedbapptest.ui.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vum.themoviedbapptest.data.models.Resource
import com.vum.themoviedbapptest.data.models.responses.ResultTopRated
import com.vum.themoviedbapptest.domine.GetTopRatedUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit

private const val API_KEY = "22cc8e67a171ee39299cb4af19ba9853"

class TopRatedViewModel(
    private val retrofit: Retrofit
): ViewModel() {

    private var _topRatedList = MutableStateFlow<List<ResultTopRated>>(listOf())
    var topRatedList = _topRatedList.asStateFlow()

    internal fun tryToGetTopRete(){
        Log.d("STEP","1")
        viewModelScope.launch(Dispatchers.IO) {

            when(val resource = GetTopRatedUseCase(retrofit).execute(apiKey = API_KEY)){
                is Resource.Success -> {
                    resource.data?.results?.forEach {
                        Log.e("LIST_OF","resultTopRated: $it")
                    }
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