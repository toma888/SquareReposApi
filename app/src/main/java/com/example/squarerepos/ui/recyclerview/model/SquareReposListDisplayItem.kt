package com.example.squarerepos.ui.recyclerview.model

import androidx.annotation.IntDef

sealed class SquareReposListDisplayItem {

    @Type
    abstract val id: Int

    data class SquareReposListItem(
        var idSquareRepos: Long = 0,
        var name: String = "",
        var starCount: Int = 0,
        var forkCount: Int = 0
    ) : SquareReposListDisplayItem() {
        override val id: Int =
            SQUARE_REPOS_LIST
    }

    companion object {
        @IntDef(
            SQUARE_REPOS_LIST
        )
        @Retention(AnnotationRetention.SOURCE)
        annotation class Type

        const val SQUARE_REPOS_LIST = 0
    }
}
