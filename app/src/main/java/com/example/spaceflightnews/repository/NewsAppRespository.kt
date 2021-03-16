package com.example.spaceflightnews.repository

import com.example.spaceflightnews.network.model.Article
import com.example.spaceflightnews.util.Resource

interface NewsAppRespository {
    suspend fun getNews(): Resource<List<Article>>
}