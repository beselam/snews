package com.example.spaceflightnews.viewModel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spaceflightnews.network.model.Article
import com.example.spaceflightnews.repository.NewsAppRespository
import com.example.spaceflightnews.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsAppViewModel @ViewModelInject constructor(private val newsAppRepository: NewsAppRespository):ViewModel() {
    private val _articles = MutableLiveData<Resource<List<Article>>>()
    val  articles : LiveData<Resource<List<Article>>> = _articles


    fun getNews () = viewModelScope.launch {
        _articles.value = Resource.Loading()
        when (val response = newsAppRepository.getNews()) {
            is Resource.Error -> response.message
            is Resource.Success -> {
                val result = response.data
                if (result == null) {
                    _articles.value = Resource.Error("something went wrong")
                } else {
                    _articles.value = Resource.Success(result)
                }
            }
        }
    }




}