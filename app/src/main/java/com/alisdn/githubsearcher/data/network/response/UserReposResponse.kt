package com.alisdn.githubsearcher.data.network.response

import com.alisdn.githubsearcher.domain.model.UserReposModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserReposResponse(
    @SerialName("name") val name: String?,
    @SerialName("description") val description: String?,
    @SerialName("updated_at") val updatedAt: String?,
    @SerialName("stargazers_count") val stars: Int?,
    @SerialName("forks") val forks: Int?,
)

fun UserReposResponse.toUserReposeModel() = UserReposModel(
    name = name,
    description = description,
    updatedAt = updatedAt,
    stars = stars,
    forks = forks
)