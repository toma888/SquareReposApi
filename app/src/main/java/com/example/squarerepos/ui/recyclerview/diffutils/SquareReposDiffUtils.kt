package com.example.squarerepos.ui.recyclerview.diffutils

import android.os.Bundle
import com.example.squarerepos.ui.recyclerview.base.BaseDiffUtils
import com.example.squarerepos.ui.recyclerview.model.SquareReposListDisplayItem

class SquareReposDiffUtils(
    private val oldList: List<SquareReposListDisplayItem>,
    private val newList: List<SquareReposListDisplayItem>
) : BaseDiffUtils<SquareReposListDisplayItem>(oldList, newList) {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? =
        when (oldList[oldItemPosition].id) {

            SquareReposListDisplayItem.SQUARE_REPOS_LIST -> {

                val bundle = Bundle()
                val oldItem =
                    oldList[oldItemPosition] as SquareReposListDisplayItem.SquareReposListItem
                val newItem =
                    newList[newItemPosition] as SquareReposListDisplayItem.SquareReposListItem

                if (oldItem.id != newItem.id) null
                else {
                    if (oldItem.idSquareRepos != newItem.idSquareRepos) {
                        bundle.putLong(PAYLOAD_REPOS_ID_CHANGED, newItem.idSquareRepos)
                    }
                    if (oldItem.name != newItem.name) {
                        bundle.putString(PAYLOAD_REPOS_NAME_CHANGED, newItem.name)
                    }
                    if (bundle.isEmpty) null
                    else bundle
                }
            }
            else -> null
        }

    companion object {
        const val PAYLOAD_REPOS_ID_CHANGED = "payload_repos_id_changed"
        const val PAYLOAD_REPOS_NAME_CHANGED = "payload_repos_name_changed"
    }
}