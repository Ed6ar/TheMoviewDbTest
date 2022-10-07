package com.vum.themoviedbapptest.data.models.responses.getTopRated

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Content(
    @SerializedName("page")
    @Expose
    val page: Int,

    @SerializedName("results")
    @Expose
    val results: List<ResultTopRated>
)
