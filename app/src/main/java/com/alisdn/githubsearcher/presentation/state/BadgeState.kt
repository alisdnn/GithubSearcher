package com.alisdn.githubsearcher.presentation.state

sealed class BadgeState {
    data object None : BadgeState()
    data object Loading : BadgeState()
    data class Finished(val forks: Long) : BadgeState()
}