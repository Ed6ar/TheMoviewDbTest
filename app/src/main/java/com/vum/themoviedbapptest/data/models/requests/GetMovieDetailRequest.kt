package com.vum.themoviedbapptest.data.models.requests

import com.vum.themoviedbapptest.data.models.responses.getMovieDetail.ResultMovieDetail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GetMovieDetailRequest {
    @GET("movie/{movieId}")
    suspend fun execute(
        @Path("movieId") movieId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Response<ResultMovieDetail>
}