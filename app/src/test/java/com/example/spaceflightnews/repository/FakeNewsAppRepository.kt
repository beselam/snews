package com.example.spaceflightnews.repository

import com.example.spaceflightnews.network.model.Article
import com.example.spaceflightnews.util.Resource

class FakeNewsAppRepository:NewsAppRespository {
    private var shouldReturnNetworkError = false

    fun setShoudReturnNetworkError(value :Boolean){
        shouldReturnNetworkError = value
    }

    override suspend fun getNews(): Resource<List<Article>> {
        return if (shouldReturnNetworkError){
            Resource.Error("error",null)

        }else{
            Resource.Success(listOf())
        }

    }
}