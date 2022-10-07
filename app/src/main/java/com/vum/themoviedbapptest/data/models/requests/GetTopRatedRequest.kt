package com.vum.themoviedbapptest.data.models.requests

import com.vum.themoviedbapptest.data.models.responses.getTopRated.Content
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GetTopRatedRequest {
    @GET("movie/top_rated?")
    suspend fun execute(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Response<Content>

}