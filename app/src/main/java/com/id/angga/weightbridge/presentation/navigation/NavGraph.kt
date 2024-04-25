package com.id.angga.weightbridge.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.id.angga.weightbridge.presentation.detail.DetailViewEvent
import com.id.angga.weightbridge.presentation.detail.DetailViewModel
import com.id.angga.weightbridge.presentation.detail.TicketDetailScreen
import com.id.angga.weightbridge.presentation.list.TicketListScreen

@Composable
fun NavGraph(
    navController : NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(
            route = Screen.Home.route
        ) {
            TicketListScreen(
                navigateToDetail = {
                    navController.navigate(
                        Screen.Detail.route+"${it.id}"
                    )
                }
            )
        }

        composable(
            route = Screen.Detail.route+"{primaryKey}",
            arguments = listOf (
                navArgument("primaryKey") {
                    type = NavType.IntType
                }
            )
        ) {
            val primaryKey = remember {
                it.arguments?.getInt("primaryKey")
            }
            TicketDetailScreen(
                primaryKey = primaryKey ?: 0,
                navigateUp = {
                    navController.popBackStack()
                }
            )
        }
    }
}

