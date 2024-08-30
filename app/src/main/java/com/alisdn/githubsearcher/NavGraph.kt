package com.alisdn.githubsearcher

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.alisdn.githubsearcher.presentation.viewmodel.SharedViewModel
import com.alisdn.githubsearcher.presentation.navigation.REPO_DETAIL_ROUTE
import com.alisdn.githubsearcher.presentation.navigation.repoDetailScreen
import com.alisdn.githubsearcher.presentation.route.SearchRoute
/**
 * Composable function representing the navigation graph for the application.
 *
 * This function sets up the navigation graph using [NavHost] and defines the routes for different screens.
 *
 * @param navController The [NavHostController] used to manage navigation.
 * @param startDestination The route of the initial screen to display. Defaults to [REPO_DETAIL_ROUTE].
 * @param onBackClick A callback function that is invoked when the back button is pressed.
 */
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