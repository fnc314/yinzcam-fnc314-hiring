package com.yinzcam.fnc314.hiring

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.yinzcam.fnc314.hiring.core.navigation.NavDestinations
import com.yinzcam.fnc314.hiring.feature.teampage.ui.TeamPage
import com.yinzcam.fnc314.hiring.feature.teampage.ui.TeamPageViewModel
import com.yinzcam.fnc314.hiring.ui.main.MainScreen

@Composable
fun MainNavigation() {
  val navController = rememberNavController()
  NavHost(
    navController = navController,
    startDestination = NavDestinations.Home,
  ) {
    composable<NavDestinations.Home> {
      MainScreen(
        modifier = Modifier,
        onItemClick = { navKey: NavDestinations -> navController.navigate(navKey) }
      )
    }
    composable<NavDestinations.TeamPage> {
      TeamPage(
        viewModel = viewModel(
          factory = TeamPageViewModel.Factory
        )
      )
    }
  }
}
