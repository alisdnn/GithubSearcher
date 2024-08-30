package com.alisdn.githubsearcher.data.network.response

import com.alisdn.githubsearcher.domain.model.UserModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data class representing a user's response from the API.
 *
 * This class is annotated with `@Serializable` foreasy serialization and deserialization with libraries like kotlinx.serialization.
 *
 * @property userName The username of the user. Mapped from the "name" field in the API response.
 * @property avatarUrl The URL of the user's avatar image. Mapped from the "avatar_url" fieldin the API response. Can be null.
 */
@Serializable
data class UserResponse(
    @SerialName("name") val userName: String,
    @SerialName("avatar_url") val avatarUrl: String?,
)

/**
 * Extension function to convert a [UserResponse] object to a [UserModel] object.
 *
 * This function maps the properties from the API response to the corresponding properties in the [UserModel] class.
 */
fun UserResponse.toUserModel() = UserModel(
    userName = userName,
    avatarUrl = avatarUrl
)