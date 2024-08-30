package com.alisdn.githubsearcher.presentation.route

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.alisdn.githubsearcher.presentation.screen.SearchScreen
import com.alisdn.githubsearcher.presentation.viewmodel.SharedViewModel
import com.alisdn.githubsearcher.presentation.viewmodel.SearchViewModel

@Composable
fun SearchRoute(
    viewModel: SearchViewModel = hiltViewModel(),
    sharedViewModel: SharedViewModel = hiltViewModel(),
    navController: NavController
) {

    val user = viewModel.userState.collectAsStateWithLifecycle()
    val isImageSectionVisible = viewModel.isImageSectionVisible.collectAsStateWithLifecycle()

    val userRepos = viewModel.userRepoState.collectAsStateWithLifecycle()
    val isReposVisible = viewModel.isReposVisible.collectAsStateWithLifecycle()

    val stopCalculation = sharedViewModel::stopCalculation

    SearchScreen(
        searchUser = viewModel::onSearchUser,
        user = user,
        isImageSectionVisible = isImageSectionVisible,
        userRepos = userRepos,
        isReposVisible = isReposVisible,
        clearStates = viewModel::clearStates,
        navController = navController,
        sumForks = sharedViewModel::sumForks,
        stopCalculation = stopCalculation,
    )
}