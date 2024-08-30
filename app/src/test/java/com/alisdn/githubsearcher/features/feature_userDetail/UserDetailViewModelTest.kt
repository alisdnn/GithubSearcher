package com.alisdn.githubsearcher.features.feature_userDetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.alisdn.githubsearcher.domain.repository.ProfileRepository
import com.alisdn.githubsearcher.domain.repository.SearchRepository
import com.alisdn.githubsearcher.presentation.model.UserItem
import com.alisdn.githubsearcher.presentation.viewmodel.SearchViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
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

    private var searchRepository: SearchRepository = mock()

    private var profileRepository: ProfileRepository = mock()

    private lateinit var viewModel: SearchViewModel

    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setup() {
        viewModel = SearchViewModel(searchRepository, profileRepository)
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `onSearchUser error and does not update state`() = runBlocking {
        val userName = "testUser"

        whenever(searchRepository.getUserRepos(userName)).thenReturn(null)
        whenever(profileRepository.getUserDetail(userName)).thenReturn(null)

        viewModel.onSearchUser(userName)

        verify(searchRepository).getUserRepos(userName)
        verify(profileRepository).getUserDetail(userName)

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