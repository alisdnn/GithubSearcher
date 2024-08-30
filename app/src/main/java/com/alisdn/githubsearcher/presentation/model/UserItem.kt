package com.alisdn.githubsearcher.presentation.model

import com.alisdn.githubsearcher.domain.model.UserModel


/**
 * Data class representing a user item for display in a list or grid.
 *
 * @property avatarUrl The URL of the user's avatar image. Can be null.
 * @property userName The username of the user.
 */
data class UserItem(
    val avatarUrl: String?,
    val userName: String,
)

/**
 * Extension function to convert a [UserModel] object to a [UserItem] object.
 *
 * This function maps the properties from [UserModel] to the corresponding properties in the [UserItem] class, providing a default empty string for `avatarUrl` if it's null.
 */
fun UserModel.toUserItem(): UserItem {
    return UserItem(
        avatarUrl = avatarUrl.orEmpty(),
        userName = userName,
    )
}