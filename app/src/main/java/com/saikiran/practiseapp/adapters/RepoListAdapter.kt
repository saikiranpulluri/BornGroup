package com.saikiran.practiseapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.saikiran.practiseapp.R
import com.saikiran.practiseapp.model.GitRepo

class RepoListAdapter(
    private val items: ArrayList<GitRepo>,
    private val onRepoSelected: OnRepoSelectedListener
) : RecyclerView.Adapter<RepoListAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val repoName: AppCompatTextView = view.findViewById(R.id.repo_name)
        val repoDescription: AppCompatTextView = view.findViewById(R.id.repo_description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_repo, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.repoName.text = item.repoName
        holder.repoDescription.text = item.description
        holder.itemView.setOnClickListener {
            onRepoSelected.onRepoSelected(item)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateRepoList(repos: List<GitRepo>) {
        items.clear()
        items.addAll(repos)
        notifyDataSetChanged()
    }

    interface OnRepoSelectedListener {
        fun onRepoSelected(selectedRepo: GitRepo)
    }
}