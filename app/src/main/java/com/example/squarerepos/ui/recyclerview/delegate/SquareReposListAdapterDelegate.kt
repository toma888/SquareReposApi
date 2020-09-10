package com.example.squarerepos.ui.recyclerview.delegate

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.NO_POSITION
import com.example.squarerepos.R
import com.example.squarerepos.ui.recyclerview.base.BaseAdapterDelegate
import com.example.squarerepos.ui.recyclerview.model.SquareReposListDisplayItem
import com.example.squarerepos.ui.recyclerview.viewholders.SquareReposListViewHolder

class SquareReposListAdapterDelegate(private val clickPerform: (SquareReposListDisplayItem.SquareReposListItem) -> Unit) :
    BaseAdapterDelegate<SquareReposListDisplayItem, SquareReposListDisplayItem.SquareReposListItem, SquareReposListViewHolder>() {
    override fun isForViewType(items: List<SquareReposListDisplayItem>, position: Int): Boolean =
        items[position].id == SquareReposListDisplayItem.SQUARE_REPOS_LIST

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
        SquareReposListViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_repository,
                parent,
                false
            )
        )

    override fun setOnClickListener(
        holder: RecyclerView.ViewHolder,
        item: List<SquareReposListDisplayItem>
    ) {
        (holder as SquareReposListViewHolder).layout.setOnClickListener {
            if (holder.adapterPosition != NO_POSITION) {
                clickPerform(item[holder.adapterPosition] as SquareReposListDisplayItem.SquareReposListItem)
            }
        }
    }

    override fun onBindViewHolder(
        item: SquareReposListDisplayItem.SquareReposListItem,
        holder: SquareReposListViewHolder
    ) = holder.bind(item)
}