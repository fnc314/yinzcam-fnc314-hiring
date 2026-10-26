package com.yinzcam.fnc314.hiring

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.contentColorFor
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.MutableCreationExtras
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.yinzcam.fnc314.hiring.core.designsystem.theme.FNC314HiringTheme
import com.yinzcam.fnc314.hiring.core.designsystem.theme.teamColor
import com.yinzcam.fnc314.hiring.core.navigation.NavDestinations
import com.yinzcam.fnc314.hiring.core.network.NetworkService
import com.yinzcam.fnc314.hiring.feature.teamschedule.data.TeamScheduleRepository
import com.yinzcam.fnc314.hiring.feature.teamschedule.domain.LoadTeamScheduleUseCase
import com.yinzcam.fnc314.hiring.feature.teamschedule.ui.TeamSchedule
import com.yinzcam.fnc314.hiring.feature.teamschedule.ui.TeamScheduleViewModel
import kotlinx.coroutines.Dispatchers

class MainActivity : ComponentActivity() {
  @OptIn(ExperimentalMaterial3Api::class)
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      FNC314HiringTheme {
        Scaffold(
          modifier = Modifier.fillMaxSize(),
          containerColor = MaterialTheme.colorScheme.surface,
          contentColor = MaterialTheme.colorScheme.onSurface,
          contentWindowInsets = WindowInsets.safeContent,
          topBar = {
            TopAppBar(
              title = {
                Text(
                  text = "Schedule".uppercase(),
                  style = MaterialTheme.typography.displaySmall,
                  color = contentColorFor(teamColor),
                  fontWeight = FontWeight.Bold,
                  modifier = Modifier,
                )
              },
              colors = TopAppBarColors(
                containerColor = teamColor,
                titleContentColor = contentColorFor(teamColor),
                scrolledContainerColor = teamColor,
                navigationIconContentColor = contentColorFor(teamColor),
                actionIconContentColor = contentColorFor(teamColor),
              ),
              navigationIcon = {
                IconButton(
                  onClick = { },
                  colors = IconButtonColors(
                    contentColor = contentColorFor(teamColor),
                    containerColor = teamColor,
                    disabledContainerColor = teamColor,
                    disabledContentColor = contentColorFor(teamColor),
                  )
                ) {
                  Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Menu",
                  )
                }
              },
            )
          },
        ) { paddingValues: PaddingValues ->
          val navController = rememberNavController()
          val modifier = Modifier.padding(paddingValues = paddingValues)
          NavHost(
            modifier = modifier.fillMaxSize(),
            navController = navController,
            startDestination = NavDestinations.TeamSchedule,
            contentAlignment = Alignment.Center,
          ) {
            composable<NavDestinations.TeamSchedule> {
              TeamSchedule(
                viewModel = viewModel(
                  factory = TeamScheduleViewModel.Factory,
                  extras = defaultViewModelCreationExtras
                ),
                modifier = Modifier.fillMaxSize(),
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
