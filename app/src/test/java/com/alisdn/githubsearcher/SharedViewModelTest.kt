package com.alisdn.githubsearcher

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.alisdn.githubsearcher.presentation.model.UserReposItem
import com.alisdn.githubsearcher.presentation.state.StarBadgeState
import com.alisdn.githubsearcher.presentation.viewmodel.SharedViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.*
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@ExperimentalCoroutinesApi
class SharedViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: SharedViewModel
    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = SharedViewModel()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `sumForks should correctly sum forks and update state to Finished`() = runBlocking {
        val repos = listOf(
            UserReposItem(
                forks = 5,
                name = null,
                description = null,
                updatedAt = null,
                stars = null
            ),
            UserReposItem(
                forks = 10,
                name = null,
                description = null,
                updatedAt = null,
                stars = null
            ),
            UserReposItem(
                forks = null,
                name = null,
                description = null,
                updatedAt = null,
                stars = null
            )
        )

        val job = launch { viewModel.sharedState.first { it is StarBadgeState.Finished } }
        viewModel.sumForks(repos)

        job.join()
        val state = viewModel.sharedState.first()
        Assert.assertTrue(state is StarBadgeState.Finished)
        if (state is StarBadgeState.Finished) {
            Assert.assertEquals(15L, state.forks)
        }
    }

    @Test
    fun `stopCalculation should cancel the job and reset state`() = runBlocking {
        val repos = listOf(
            UserReposItem(
                forks = 5,
                name = null,
                description = null,
                updatedAt = null,
                stars = null
            ),
            UserReposItem(
                forks = 10,
                name = null,
                description = null,
                updatedAt = null,
                stars = null
            )
        )

        viewModel.sumForks(repos, 4000L)
        val loadingState = viewModel.sharedState.first { it is StarBadgeState.Loading }

        viewModel.stopCalculation()
        val state = viewModel.sharedState.first()

        Assert.assertTrue(loadingState is StarBadgeState.Loading)
        Assert.assertTrue(state is StarBadgeState.None)
    }
}