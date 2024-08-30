package com.alisdn.githubsearcher.presentation.state

sealed class StarBadgeState {
    data object None : StarBadgeState()
    data object Loading : StarBadgeState()
    data class Finished(val forks: Long) : StarBadgeState()
}