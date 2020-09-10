package com.example.squarerepos.data.mapper

import com.example.squarerepos.network.model.DetailSquareReposResponse
import com.example.squarerepos.ui.squaredetail.DetailSquareRepos

internal fun DetailSquareReposResponse.toDisplaySquareRepos(): DetailSquareRepos {
    return DetailSquareRepos(
        name = name,
        id = id,
        starCount = starCount.toString(),
        forkCount = forkCount.toString(),
        fullName = fullName,
        description = description ?: "Description is not defined",
        url = url,
        language = language ?: "Language is not defined",
        watcherCount = watcherCount.toString(),
        issuesCount = issuesCount.toString()
    )
}