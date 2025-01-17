package com.alisdn.githubsearcher

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.alisdn.githubsearcher.ui.theme.GithubSearcherTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GithubSearcherTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AddNavigation()
                }
            }
        }
    }
}

@Composable
fun AddNavigation(){
    val navController: NavHostController = rememberNavController()
    NavGraph(navController) {
        navController.navigateUp()
    }
}
