package com.alisdn.githubsearcher.data.network.response

import com.alisdn.githubsearcher.domain.model.UserReposModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data class representing a user's repository response from the API.
 *
 * This class is annotated with `@Serializable`for easy serialization and deserialization with libraries like kotlinx.serialization.
 *
 * @property name The name of the repository. Mapped from the "name" field in the API response. Can be null.
 * @property description A brief description of the repository. Mapped from the "description" field inthe API response. Can be null.
 * @property updatedAt The date and time when the repository was last updated. Mapped from the "updated_at" field in the API response. Can be null.
 * @property stars The number of stars the repository has received. Mapped from the "stargazers_count" field in the API response. Can be null.
 * @property forks The number of times the repository has been forked. Mapped from the "forks" field in the API response. Can be null.
 */
@Serializable
data class UserReposResponse(
    @SerialName("name") val name: String?,
    @SerialName("description") val description: String?,
    @SerialName("updated_at") val updatedAt: String?,
    @SerialName("stargazers_count") val stars: Int?,
    @SerialName("forks") val forks: Int?,
)

/**
 * Extension function to convert a [UserReposResponse] object to a [UserReposModel] object.
 *
 * This function maps the properties from the API response to the corresponding properties in the [UserReposModel] class.
 */
fun UserReposResponse.toUserReposeModel() = UserReposModel(
    name = name,
    description = description,
    updatedAt = updatedAt,
    stars = stars,
    forks = forks
)