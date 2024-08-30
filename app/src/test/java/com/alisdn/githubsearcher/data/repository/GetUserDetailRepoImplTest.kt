package com.alisdn.githubsearcher.data.repository

import com.alisdn.githubsearcher.data.network.ApiService
import com.alisdn.githubsearcher.domain.model.UserModel
import com.alisdn.githubsearcher.domain.repository.ProfileRepository
import com.alisdn.githubsearcher.util.CoroutinesTestRule
import kotlinx.coroutines.test.runTest
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import org.hamcrest.CoreMatchers.`is`


@RunWith(MockitoJUnitRunner::class)
class GetUserDetailRepoImplTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private val apiService: ApiService = mock()
    private lateinit var profileRepository: ProfileRepository

    @Before
    fun setUp() {
        profileRepository = ProfileRepository(apiService)
    }

    @Test
    fun `response should be get successfully when getUsersRepository is called`() = runTest {
        // Given
        val userName = "test"
        val avatarUrl = "testUrl"
        val response = UserModel(userName, avatarUrl)
        whenever(profileRepository.getUserDetail(userName)) doReturn response

        // When
        val result = profileRepository.getUserDetail(userName)

        // Then
        MatcherAssert.assertThat(result, `is`(response))
        verify(profileRepository).getUserDetail(userName)
    }
}