package com.example.spaceflightnews.repository

import com.example.spaceflightnews.network.NewsApi
import com.example.spaceflightnews.network.model.Article
import com.example.spaceflightnews.util.Resource
import javax.inject.Inject

class DefaultNewsAppRespository @Inject constructor(private val  newsApi:NewsApi):NewsAppRespository {

    override suspend fun getNews(): Resource<List<Article>> {
        return try {
            val response = newsApi.getNews()
            if(response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.Success(it)
                }?:Resource.Error("unknown error occured",null)
            } else {
                Resource.Error("error occurred",null)
            }
        } catch(e: Exception) {
            Resource.Error(e.message ?: "Couldn't reach the server. check your internet connection",null)
        }
    }
}