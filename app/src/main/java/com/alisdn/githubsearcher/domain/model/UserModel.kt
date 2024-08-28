package com.alisdn.githubsearcher.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserModel(
    @SerialName("login") val userName: String,
    @SerialName("avatar_url") val avatarUrl: String?,
)