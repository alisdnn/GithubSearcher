package com.alisdn.githubsearcher.domain.model

data class UserReposModel(
    val name: String?,
    val description: String?,
    val updatedAt: String?,
    val stars: Int?,
    val forks: Int?,
)