package com.saikiran.practiseapp.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.saikiran.practiseapp.databinding.ActivityMainBinding
import com.saikiran.practiseapp.model.GitRepo
import com.saikiran.practiseapp.util.DataState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        subscribeObservers()
        viewModel.setStateEvent(MainStateEvent.GetReposEvents)

        binding.retry.setOnClickListener {
            viewModel.setStateEvent(MainStateEvent.GetReposEvents)
        }
    }

    private fun subscribeObservers() {
        viewModel.dataState.observe(this) { dataState ->
            when (dataState) {
                is DataState.Success<List<GitRepo>> -> {
                    handleLoading(false) // After successful result hide progress
                    // Update the recyclerview with latest data
                    Log.i(TAG, dataState.data.toString())
                }
                is DataState.Error -> {
                    handleLoading(false) // If any error hide progress first
                    handleError()
                }
                is DataState.Loading -> {
                    handleLoading(true) // Show progress till results are fetched
                }
            }
        }
    }

    private fun handleError() {
        binding.message.visibility = View.VISIBLE
        binding.retry.visibility = View.GONE
    }

    private fun handleLoading(isDisplayed: Boolean) {
        binding.progressBar.visibility = if (isDisplayed) View.VISIBLE else View.GONE
    }

    private fun handleReposData(repos: List<GitRepo>) {

    }
}