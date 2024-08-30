package com.alisdn.githubsearcher.presentation.model

import com.alisdn.githubsearcher.domain.model.UserModel


data class UserItem(
    val avatarUrl: String?,
    val userName: String,
)

fun UserModel.toUserItem(): UserItem {
    return UserItem(
        avatarUrl = avatarUrl.orEmpty(),
        userName = userName,
    )
}