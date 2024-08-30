package com.alisdn.githubsearcher.domain.model

/**
 * Data class representing a user.
 *
 * @property userName The username of the user.
 * @property avatarUrl TheURL of the user's avatar image. Can be null.
 */
data class UserModel(
    val userName: String,
    val avatarUrl: String?,
)