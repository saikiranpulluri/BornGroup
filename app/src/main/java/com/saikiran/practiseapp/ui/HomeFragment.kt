package com.saikiran.practiseapp.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.saikiran.practiseapp.R
import com.saikiran.practiseapp.adapters.RepoListAdapter
import com.saikiran.practiseapp.databinding.FragmentHomeBinding
import com.saikiran.practiseapp.model.GitRepo
import com.saikiran.practiseapp.util.DataState
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "HomeFragment"

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var repoListAdapter: RepoListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        repoListAdapter =
            RepoListAdapter(arrayListOf(), object : RepoListAdapter.OnRepoSelectedListener {
                override fun onRepoSelected(selectedRepo: GitRepo) {
                    val bundle = Bundle()
                    bundle.putSerializable("gitRepo", selectedRepo)
                    findNavController().navigate(R.id.action_home_to_repoDetails, bundle)
                }
            })

        binding.reposList.adapter = repoListAdapter
        subscribeObservers()
        viewModel.setStateEvent(MainStateEvent.GetReposEvents)

        binding.retry.setOnClickListener {
            viewModel.setStateEvent(MainStateEvent.GetReposEvents)
        }
    }

    private fun subscribeObservers() {
        viewModel.dataState.observe(viewLifecycleOwner) { dataState ->
            when (dataState) {
                is DataState.Success<List<GitRepo>> -> {
                    Log.i(TAG, dataState.data.toString())
                    handleLoading(false) // After successful result hide progress
                    // Update the recyclerview with latest data
                    handleReposData(dataState.data)
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
        binding.retry.visibility = View.VISIBLE
    }

    private fun handleLoading(isDisplayed: Boolean) {
        binding.progressBar.visibility = if (isDisplayed) View.VISIBLE else View.GONE
        binding.message.visibility = View.GONE
        binding.retry.visibility = View.GONE
    }

    private fun handleReposData(repos: List<GitRepo>) {
        binding.reposList.visibility = View.VISIBLE
        repoListAdapter.updateRepoList(repos)
    }
}