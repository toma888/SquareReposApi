package com.example.squarerepos.ui.recyclerview.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.squarerepos.ui.recyclerview.model.SquareReposListItem
import com.example.squarerepos.ui.recyclerview.viewholders.SquareReposListViewHolder

class ReposAdapter(private val clickPerform: (SquareReposListItem) -> Unit) :
    PagingDataAdapter<SquareReposListItem, ViewHolder>(REPO_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return SquareReposListViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { item ->
            (holder as SquareReposListViewHolder).bind(item)
            holder.itemView.setOnClickListener { clickPerform(item) }
        }
    }

    companion object {
        private val REPO_COMPARATOR =
            object : DiffUtil.ItemCallback<SquareReposListItem>() {
                override fun areItemsTheSame(
                    oldItem: SquareReposListItem,
                    newItem: SquareReposListItem
                ): Boolean {
                    return (oldItem.idSquareRepos == newItem.idSquareRepos)
                }

                override fun areContentsTheSame(
                    oldItem: SquareReposListItem,
                    newItem: SquareReposListItem
                ): Boolean =
                    oldItem == newItem
            }
    }
}