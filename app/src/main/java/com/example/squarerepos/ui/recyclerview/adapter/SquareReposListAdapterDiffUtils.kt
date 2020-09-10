package com.example.squarerepos.ui.recyclerview.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.squarerepos.ui.recyclerview.base.AdapterDelegatesManager
import com.example.squarerepos.ui.recyclerview.base.BaseListAdapterDiffUtils
import com.example.squarerepos.ui.recyclerview.diffutils.SquareReposDiffUtils
import com.example.squarerepos.ui.recyclerview.model.SquareReposListDisplayItem

open class SquareReposListAdapterDiffUtils(
    adapterDelegatesManager:
    AdapterDelegatesManager<SquareReposListDisplayItem>
) : BaseListAdapterDiffUtils<SquareReposListDisplayItem>(adapterDelegatesManager) {

    override fun getDiffUtilsResult(list: MutableList<SquareReposListDisplayItem>): DiffUtil.DiffResult =
        DiffUtil.calculateDiff(SquareReposDiffUtils(listOfItems, list))
}