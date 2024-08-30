package com.alisdn.githubsearcher.presentation.state

/**
 * Represents the different states of a star badge.
 *
 * This sealed class defines the possible states of a star badge, which can be used to display different UI elements or handle different logic based on the current state.
 *
 * @see None
 * @see Loading
 * @see Finished
 */
sealed class StarBadgeState {
    /**
     * Represents the state where there is no star badge.
     */data object None : StarBadgeState()

    /**
     * Represents the state where the star badge is loading.
     */
    data object Loading : StarBadgeState()

    /**
     * Represents the state where the star badge has finished loading.
     *
     * @property forks The number of forks for the repository.
     */
    data class Finished(val forks: Long) : StarBadgeState()
}