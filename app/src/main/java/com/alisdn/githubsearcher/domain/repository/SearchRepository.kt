package com.alisdn.githubsearcher.domain.repository

import com.alisdn.githubsearcher.data.network.ApiService
import com.alisdn.githubsearcher.data.network.response.toUserReposeModel
import com.alisdn.githubsearcher.domain.model.UserReposModel
import javax.inject.Inject

class SearchRepository
@Inject constructor(
    private val apiService: ApiService
) {
    /**
     * Retrieves a list of repositories for the given username.
     *
     * This function makes a network request to fetch the user'srepositories and maps the results to a list of [UserReposModel] objects.
     *
     * @param username The username of the user to retrieve repositories for.
     * @return A list of [UserReposModel] objects representing the user's repositories.
     * @suspend This function suspends the coroutinewhile making the network request.
     */
    suspend fun getUserRepos(username: String): List<UserReposModel> {
        return apiService.getUserRepos(username).map { it.toUserReposeModel() }
    }
}
