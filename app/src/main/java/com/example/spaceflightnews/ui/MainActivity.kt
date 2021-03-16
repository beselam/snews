package com.example.spaceflightnews.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spaceflightnews.adapter.NewsAppAdapter
import com.example.spaceflightnews.databinding.ActivityMainBinding
import com.example.spaceflightnews.util.Resource
import com.example.spaceflightnews.viewModel.NewsAppViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private  val newsAppViewModel: NewsAppViewModel by viewModels()


    private lateinit var binding: ActivityMainBinding
    lateinit var newsAppAdapter: NewsAppAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()
        binding.getNewsFabButton.setOnClickListener {

            binding.userGuidTextView.visibility = View.GONE
            newsAppViewModel.getNews()

        }


        newsAppViewModel.articles.observe(this, Observer {response ->
            when(response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { newsResponse ->
                        newsAppAdapter.differ.submitList(newsResponse)
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Toast.makeText(applicationContext,message,Toast.LENGTH_LONG).show()
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }

        })
    }
    private fun hideProgressBar() {
        binding.paginationProgressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        binding.paginationProgressBar.visibility = View.VISIBLE
    }

    private fun setupRecyclerView() {
        newsAppAdapter = NewsAppAdapter()
        binding.rvBreakingNews.apply {
            adapter = newsAppAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }


}