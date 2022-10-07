package com.vum.themoviedbapptest.domine

import com.vum.themoviedbapptest.R
import com.vum.themoviedbapptest.data.models.Resource
import com.vum.themoviedbapptest.data.models.requests.GetMovieDetailRequest
import com.vum.themoviedbapptest.data.models.responses.getMovieDetail.ResultMovieDetail
import retrofit2.Retrofit

class GetMovieDetailUseCase(
    private val retrofit: Retrofit
) {

    suspend fun execute(apiKey: String, movieId: Int): Resource<ResultMovieDetail> {
        val getMovieDetail = retrofit.create(GetMovieDetailRequest::class.java)
        val response = getMovieDetail.execute(
            movieId = movieId,
            apiKey = apiKey,
            language = "en-US"
        )

        val result = response.body()

        return try {
            throw Exception()
            if(response.isSuccessful && result != null)
                Resource.Success(data = result)
            else
                Resource.Error(data = null, message = response.errorBody().toString())
        }catch (e: Exception){
            Resource.Exception(message = R.string.unexpectedErrorLoadingMovieDetail)
        }
    }

}