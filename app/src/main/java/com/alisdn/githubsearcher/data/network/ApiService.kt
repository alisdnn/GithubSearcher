package com.alisdn.githubsearcher.data.network

import com.alisdn.githubsearcher.data.network.response.UserReposResponse
import com.alisdn.githubsearcher.data.network.response.UserResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("users/{userId}")
    suspend fun getUserDetail(
        @Path("userId") username: String
    ): UserResponse

    @GET("users/{userId}/repos")
    suspend fun getUserRepos(
        @Path("userId") username: String
    ): List<UserReposResponse>

}