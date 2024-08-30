package com.alisdn.githubsearcher.domain.repository

import com.alisdn.githubsearcher.data.network.ApiService
import com.alisdn.githubsearcher.data.network.response.toUserModel
import com.alisdn.githubsearcher.domain.model.UserModel
import javax.inject.Inject

class ProfileRepository
@Inject constructor(
    private val apiService: ApiService
) {

    /**
     * Retrieves user details for the given username.
     *
     * This function makes a network request to fetch user details and maps the resultto a [UserModel].
     *
     * @param username The username of the user to retrieve details for.
     * @return The [UserModel] representing the user's details.
     * @suspend This function suspends the coroutine while making the network request.
     */
    suspend fun getUserDetail(username: String): UserModel {
        return apiService.getUserDetail(username).toUserModel()
    }
}