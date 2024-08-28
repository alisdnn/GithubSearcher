package com.alisdn.githubsearcher.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserReposModel(
    @SerialName("name") val name: String?,
    @SerialName("description") val description: String?,
    @SerialName("updated_at") val updatedAt: String?,
    @SerialName("stargazers_count") val stars: Int?,
    @SerialName("forks") val forks: Int?,
)