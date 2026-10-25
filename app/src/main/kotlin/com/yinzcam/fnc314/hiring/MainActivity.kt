package com.yinzcam.fnc314.hiring

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.MutableCreationExtras
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.yinzcam.fnc314.hiring.core.data.NFLTeamsTriCode
import com.yinzcam.fnc314.hiring.core.designsystem.theme.FNC314HiringTheme
import com.yinzcam.fnc314.hiring.core.navigation.NFLTeamsTriCodeType
import com.yinzcam.fnc314.hiring.core.navigation.NavDestinations
import com.yinzcam.fnc314.hiring.core.network.NetworkService
import com.yinzcam.fnc314.hiring.feature.teamschedule.data.TeamScheduleRepository
import com.yinzcam.fnc314.hiring.feature.teamschedule.domain.LoadTeamScheduleUseCase
import com.yinzcam.fnc314.hiring.feature.teamschedule.ui.TeamSchedule
import com.yinzcam.fnc314.hiring.feature.teamschedule.ui.TeamScheduleViewModel
import com.yinzcam.fnc314.hiring.ui.main.MainScreen
import kotlinx.coroutines.Dispatchers
import kotlin.reflect.typeOf

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      FNC314HiringTheme {
        Scaffold(
          modifier = Modifier.fillMaxSize(),
          containerColor = MaterialTheme.colorScheme.surface,
          contentColor = MaterialTheme.colorScheme.onSurface,
          contentWindowInsets = ScaffoldDefaults.contentWindowInsets,
          topBar = {

          },
        ) { paddingValues: PaddingValues ->
          val navController= rememberNavController()
          val modifier = Modifier.consumeWindowInsets(paddingValues = paddingValues)
          NavHost(
            navController = navController,
            startDestination = NavDestinations.Home,
          ) {
            composable<NavDestinations.Home> {
              MainScreen(
                modifier = modifier,
                navController = navController,
                onItemClick = { navKey: NavDestinations -> navController.navigate(navKey) }
              )
            }
            composable<NavDestinations.TeamSchedule>(
              typeMap = mapOf(typeOf<NFLTeamsTriCode>() to NFLTeamsTriCodeType)
            ) {
              TeamSchedule(
                viewModel = viewModel(
                  factory = TeamScheduleViewModel.Factory,
                  extras = defaultViewModelCreationExtras
                ),
                modifier = modifier,
              )
            }
          }
        }
      }
    }
  }

  override val defaultViewModelCreationExtras: CreationExtras
    get() = MutableCreationExtras(
      super.defaultViewModelCreationExtras
    ).apply {
      set(
        LoadTeamScheduleUseCase,
        LoadTeamScheduleUseCase.newInstance(
          repository = TeamScheduleRepository.create(
            dispatcher = Dispatchers.IO,
            networkService = NetworkService.DEFAULT,
          )
        )
      )
    }
}
