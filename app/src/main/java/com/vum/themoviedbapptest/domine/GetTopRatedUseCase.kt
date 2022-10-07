package com.vum.themoviedbapptest.domine

import com.vum.themoviedbapptest.R
import com.vum.themoviedbapptest.data.models.Resource
import com.vum.themoviedbapptest.data.models.requests.GetTopRatedRequest
import com.vum.themoviedbapptest.data.models.responses.getTopRated.Content
import retrofit2.Retrofit

class GetTopRatedUseCase(
    private val retrofit: Retrofit
) {
    suspend fun execute(apiKey: String): Resource<Content> {

        val getTopRated = retrofit.create(GetTopRatedRequest::class.java)
        val response = getTopRated.execute(
            apiKey = apiKey,
            language = "en-US",
            page = 1
        )

        val result = response.body()

        return try {
            if (response.isSuccessful && result != null)
                Resource.Success(data = result)
            else
                Resource.Error(data = null, message = response.errorBody().toString())
        } catch (e: Exception) {
            Resource.Exception(message = R.string.unexpectedErrorLoadingTopRate)
        }
    }

}