package com.example.squarerepos.ui.recyclerview.viewholders

import android.view.View
import android.view.ViewGroup
import com.example.squarerepos.R
import com.example.squarerepos.core.inflate
import com.example.squarerepos.ui.recyclerview.base.BaseViewHolder
import com.example.squarerepos.ui.recyclerview.model.SquareReposListDisplayItem
import kotlinx.android.synthetic.main.item_repository.view.*

class SquareReposListViewHolder(view: View) :
    BaseViewHolder<SquareReposListDisplayItem.SquareReposListItem>(view) {

    internal val layout = view.layout
    private val name = view.tv_name
    private val star = view.tv_star
    private val fork = view.tv_fork


    override fun bind(item: SquareReposListDisplayItem.SquareReposListItem) {
        name.text = item.name
        star.text = item.starCount.toString()
        fork.text = item.forkCount.toString()
    }

    companion object {
        fun from(parent: ViewGroup) =
            SquareReposListViewHolder(
                parent.inflate(R.layout.item_repository)
            )
    }
}
