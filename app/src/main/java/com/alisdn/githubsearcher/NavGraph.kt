package com.alisdn.githubsearcher

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.alisdn.githubsearcher.presentation.ProfileScreen
import com.alisdn.githubsearcher.presentation.SearchScreen

@Composable
fun NavGraph(startDestination: String = "search_list") {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {
        composable("search_list") {
            SearchScreen()
        }
        composable("profile_details/{catId}") { backStackEntry ->
            ProfileScreen()
        }
    }
}
