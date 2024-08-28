package com.alisdn.githubsearcher.domain.model

import com.alisdn.githubsearcher.data.network.ApiService
import com.alisdn.githubsearcher.data.network.toUserReposeModel
import javax.inject.Inject

class SearchRepository
@Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getUserRepos(username: String): List<UserReposModel> {
        return apiService.getUserRepos(username).map { it.toUserReposeModel() }
    }
}

// TODO: add IMPL classes