package com.example.squarerepos.data.mapper

import com.example.squarerepos.network.model.SquareReposResponse
import com.example.squarerepos.ui.recyclerview.model.SquareReposListDisplayItem

internal fun SquareReposResponse.toDisplaySquareRepos(): SquareReposListDisplayItem {
    return SquareReposListDisplayItem.SquareReposListItem(
        name = name,
        idSquareRepos = id,
        starCount = starCount,
        forkCount = forkCount
    )
}

internal fun List<SquareReposResponse>.toDisplayListSquareRepos(): List<SquareReposListDisplayItem> {
    return this.map { it.toDisplaySquareRepos() }
}