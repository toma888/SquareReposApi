package com.example.squarerepos.ui.recyclerview.base

import androidx.recyclerview.widget.DiffUtil

abstract class BaseListAdapterDiffUtils<Base>(
    private val adapterDelegatesManager: AdapterDelegatesManager<Base>
) : BaseListAdapter<Base>(adapterDelegatesManager) {

    override fun setItems(newArrayList: MutableList<Base>) {
        getDiffUtilsResult(newArrayList).dispatchUpdatesTo(this)
        super.setItems(newArrayList)
    }

    abstract fun getDiffUtilsResult(list: MutableList<Base>): DiffUtil.DiffResult
}