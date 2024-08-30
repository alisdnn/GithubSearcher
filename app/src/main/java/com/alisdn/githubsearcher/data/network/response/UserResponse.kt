package com.alisdn.githubsearcher.data.network.response

import com.alisdn.githubsearcher.domain.model.UserModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    @SerialName("name") val userName: String,
    @SerialName("avatar_url") val avatarUrl: String?,
)

fun UserResponse.toUserModel() = UserModel(
    userName = userName,
    avatarUrl = avatarUrl
)