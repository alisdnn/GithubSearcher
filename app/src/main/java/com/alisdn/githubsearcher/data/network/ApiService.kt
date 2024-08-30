package com.alisdn.githubsearcher.data.network

import com.alisdn.githubsearcher.data.network.response.UserReposResponse
import com.alisdn.githubsearcher.data.network.response.UserResponse
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Retrofit API service interface for fetching user data.
 *
 * This interface defines the endpoints for retrieving user details and repositories from aremote server.
 */
interface ApiService {

    /**
     * Retrieves details for a specific user.
     *
     * @param username The username or ID of the user.
     * @return A [UserResponse] object containing the user's details.
     * @suspend This functionsuspends the coroutine while making the network request.
     */
    @GET("users/{userId}")
    suspend fun getUserDetail(
        @Path("userId") username: String
    ): UserResponse

    /**
     * Retrieves a list of repositories for a specific user.
     *
     * @param username The username or ID of the user.
     * @return A list of [UserReposResponse] objects, each representing a repository.
     * @suspend This function suspends the coroutine while making the network request.
     */
    @GET("users/{userId}/repos")
    suspend fun getUserRepos(
        @Path("userId") username: String
    ): List<UserReposResponse>

}