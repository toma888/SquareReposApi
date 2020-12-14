package com.example.squarerepos.data.mapper

import com.example.squarerepos.network.model.SquareReposResponse
import com.example.squarerepos.ui.recyclerview.model.SquareReposListItem

internal fun SquareReposResponse.toDisplaySquareRepos(): SquareReposListItem {
    return SquareReposListItem(
        name = name,
        idSquareRepos = id,
        starCount = starCount,
        forkCount = forkCount
    )
}

internal fun List<SquareReposResponse>.toDisplayListSquareRepos(): List<SquareReposListItem> {
    return this.map { it.toDisplaySquareRepos() }
}