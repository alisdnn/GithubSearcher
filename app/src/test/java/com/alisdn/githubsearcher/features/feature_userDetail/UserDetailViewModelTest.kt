package com.example.githubuserfinder.features.feature_userDetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import arrow.core.left
import com.example.domain.model.error.NetworkError
import com.example.domain.usecase.GetUserDetailUseCase
import com.example.domain.usecase.GetUserReposRepoUseCase
import com.example.githubuserfinder.features.feature_userDetail.model.UserItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.setMain
import kotlinx.coroutines.test.resetMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.verify
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class UserDetailViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private var getUserDetailUseCase: GetUserDetailUseCase = mock()

    private var getUserReposRepoUseCase: GetUserReposRepoUseCase = mock()

    private lateinit var viewModel: UserDetailViewModel

    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setup() {
        viewModel = UserDetailViewModel(getUserDetailUseCase, getUserReposRepoUseCase)
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `onSearchUser error logs error and does not update state`() = runBlocking {
        val userName = "testUser"
        val networkError = NetworkError.NotDefined(Throwable())

        whenever(getUserDetailUseCase.getUserDetail(userName)).thenReturn(networkError.left())
        whenever(getUserReposRepoUseCase.getUserRepos(userName)).thenReturn(networkError.left())

        viewModel.onSearchUser(userName)

        verify(getUserDetailUseCase).getUserDetail(userName)
        verify(getUserReposRepoUseCase).getUserRepos(userName)

        assertEquals(UserItem("", ""), viewModel.userState.value)
        assertFalse(viewModel.isImageSectionVisible.value.targetState)
        assertTrue(viewModel.userRepoState.value.isEmpty())
        assertFalse(viewModel.isReposVisible.value.targetState)
    }

    @Test
    fun `clearStates resets userRepoState and userState`() = runBlocking {
        viewModel.clearStates()

        assertTrue(viewModel.userRepoState.value.isEmpty())
        assertEquals(UserItem(null, ""), viewModel.userState.value)
    }
}