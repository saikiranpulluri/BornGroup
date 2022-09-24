package com.saikiran.practiseapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.saikiran.practiseapp.databinding.FragmentRepoDetailsBinding
import com.saikiran.practiseapp.model.GitRepo
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RepoDetailsFragment : Fragment() {
    private var _binding: FragmentRepoDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRepoDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            val repo = it.getSerializable("gitRepo") as GitRepo
            binding.repoId.text = repo.id.toString()
            binding.repoName.text = repo.repoName
            binding.repoDescription.text = repo.description
            binding.repoVisibility.text = repo.visibility
        }
    }
}