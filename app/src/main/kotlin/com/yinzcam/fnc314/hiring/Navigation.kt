package com.yinzcam.fnc314.hiring

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.yinzcam.fnc314.hiring.core.navigation.NavDestinations
import com.yinzcam.fnc314.hiring.feature.teamschedule.ui.TeamSchedule
import com.yinzcam.fnc314.hiring.feature.teamschedule.ui.TeamScheduleViewModel
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
        navController = navController,
        onItemClick = { navKey: NavDestinations -> navController.navigate(navKey) }
      )
    }
    composable<NavDestinations.TeamSchedule> {
      TeamSchedule(
        viewModel = viewModel(
          factory = TeamScheduleViewModel.Factory
        )
      )
    }
  }
}
