package com.example.spaceflightnews.network

import com.example.spaceflightnews.network.model.Article
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("v2/articles")
    suspend fun getNews(@Query("_limit") limit:Int=7): Response<List<Article>>
}