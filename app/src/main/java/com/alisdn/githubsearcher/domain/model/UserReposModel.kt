package com.alisdn.githubsearcher.domain.model

/**
 * Data class representing a user's repository.
 *
 * @property name The name of the repository. Can be null.* @property description A brief description of the repository. Can be null.
 * @property updatedAt The date and time when the repository was last updated. Can be null.
 * @property stars The number of stars the repository has received. Can be null.
 * @property forks The number of times therepository has been forked. Can be null.
 */
data class UserReposModel(
    val name: String?,
    val description: String?,
    val updatedAt: String?,
    val stars: Int?,
    val forks: Int?,
)