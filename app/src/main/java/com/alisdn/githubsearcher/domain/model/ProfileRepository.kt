package com.alisdn.githubsearcher.domain.model

import com.alisdn.githubsearcher.data.network.ApiService
import com.alisdn.githubsearcher.data.network.toUserModel
import javax.inject.Inject

class ProfileRepository
@Inject constructor(
    private val apiService: ApiService
) {
    suspend fun searchUserDetail(username: String): UserModel {
        return apiService.getUserDetail(username).toUserModel()
    }
}