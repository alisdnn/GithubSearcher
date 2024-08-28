package com.alisdn.githubsearcher

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.alisdn.githubsearcher.presentation.SharedViewModel
import com.alisdn.githubsearcher.presentation.navigation.REPO_DETAIL_ROUTE
import com.alisdn.githubsearcher.presentation.navigation.repoDetailScreen
import com.alisdn.githubsearcher.presentation.route.SearchRoute

@Composable
fun NavGraph(
    navController: NavHostController,
    startDestination: String = REPO_DETAIL_ROUTE,
    onBackClick: () -> Unit,
) {

    val sharedViewModel: SharedViewModel = hiltViewModel()

    NavHost(navController = navController, startDestination = startDestination) {
        composable(REPO_DETAIL_ROUTE) {
            SearchRoute(
                sharedViewModel = sharedViewModel,
                navController = navController
            )
        }
        repoDetailScreen(onBackClick, sharedViewModel)

    }
}
