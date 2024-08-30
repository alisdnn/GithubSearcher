package com.alisdn.githubsearcher.presentation.model

import com.alisdn.githubsearcher.domain.model.UserReposModel

/**
 * Data class representing a user's repository item for display in a list or grid.
 *
 * @property name The nameof the repository. Can be null.
 * @property description A brief description of the repository. Can be null.
 * @property updatedAt The date and time when the repository was last updated. Can be null.
 * @property stars The number of stars the repository has received. Can be null.
 *@property forks The number of times the repository has been forked. Can be null.
 */
data class UserReposItem(
    val name: String?,
    val description: String?,
    val updatedAt: String?,
    val stars: Int?,
    val forks: Int?,
)

/**
 * Extension function to convert a [UserReposModel] object to a [UserReposItem] object.
 *
 * This function maps the properties from [UserReposModel] to the corresponding properties in the [UserReposItem] class.
 */
fun UserReposModel.toUserReposItem():UserReposItem {
    return UserReposItem(
        description = description,
        name = name,
        updatedAt = updatedAt,
        stars = stars,
        forks = forks,
    )
}