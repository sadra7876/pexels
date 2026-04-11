package com.example.core.navigation.navHost

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.navigation
import com.example.core.navigation.graphs.mainGraph

@Composable
fun NavHostContent(
    modifier: Modifier = Modifier,
    initialRout: String = "MainScreens",
    startDestination: String = "PhotosScreen",
    navController: NavHostController
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = initialRout,
        ) {
            navigation(route="MainScreens" ,startDestination = startDestination,){
                mainGraph(navController)
            }
    }
}