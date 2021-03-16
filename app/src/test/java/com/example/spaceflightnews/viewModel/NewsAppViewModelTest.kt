package com.example.spaceflightnews.viewModel

import com.example.spaceflightnews.repository.FakeNewsAppRepository
import org.junit.Assert.*
import org.junit.Before

class NewsAppViewModelTest{
    private lateinit var viewModel: NewsAppViewModel
    @Before
    fun setUp() {
        viewModel= NewsAppViewModel(FakeNewsAppRepository())
    }

}