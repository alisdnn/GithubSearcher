package com.alisdn.githubsearcher.data.network

import com.alisdn.githubsearcher.domain.model.UserModel
import com.alisdn.githubsearcher.domain.model.UserReposModel
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("users/{userId}")
    suspend fun getUserDetail(
        @Path("userId") username: String
    ): UserModel

    @GET("users/{userId}/repos")
    suspend fun getUserRepos(
        @Path("userId") username: String
    ): List<UserReposModel>

}