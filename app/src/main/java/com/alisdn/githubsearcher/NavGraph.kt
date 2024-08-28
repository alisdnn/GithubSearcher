package com.alisdn.githubsearcher

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.alisdn.githubsearcher.presentation.RepoDetailScreen
import com.alisdn.githubsearcher.presentation.SharedViewModel
import com.alisdn.githubsearcher.presentation.route.SearchRoute

@Composable
fun NavGraph(startDestination: String = "search_list") {
    val navController = rememberNavController()
    val sharedViewModel: SharedViewModel = hiltViewModel()

    NavHost(navController = navController, startDestination = startDestination) {
        composable("search_list") {
            SearchRoute(
                sharedViewModel = sharedViewModel,
                navController = navController
            )
        }
        composable("profile_details/{catId}") { backStackEntry ->
            RepoDetailScreen()
        }
    }
}
