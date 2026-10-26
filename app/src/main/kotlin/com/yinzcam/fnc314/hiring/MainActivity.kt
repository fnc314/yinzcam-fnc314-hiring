package com.yinzcam.fnc314.hiring

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.safeContent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
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
import com.yinzcam.fnc314.hiring.core.navigation.NavDestinations
import com.yinzcam.fnc314.hiring.core.network.NetworkService
import com.yinzcam.fnc314.hiring.feature.teamschedule.data.TeamScheduleRepository
import com.yinzcam.fnc314.hiring.feature.teamschedule.domain.LoadTeamScheduleUseCase
import com.yinzcam.fnc314.hiring.feature.teamschedule.ui.TeamSchedule
import com.yinzcam.fnc314.hiring.feature.teamschedule.ui.TeamScheduleViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
  @OptIn(ExperimentalMaterial3Api::class)
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      val navController = rememberNavController()
      val dynamicColors = rememberSaveable { mutableStateOf(false) }
      val snackbarHostState = remember { SnackbarHostState() }
      val scope = rememberCoroutineScope()

      FNC314HiringTheme(
        dynamicColor = dynamicColors.value,
      ) {
        Scaffold(
          modifier = Modifier.fillMaxSize(),
          containerColor = MaterialTheme.colorScheme.surface,
          contentColor = MaterialTheme.colorScheme.onSurface,
          contentWindowInsets = WindowInsets.safeContent,
          snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
          },
          topBar = {
            CenterAlignedTopAppBar(
              title = {
                Text(
                  text = "Schedule".uppercase(),
                  style = MaterialTheme.typography.displaySmall,
                  color = MaterialTheme.colorScheme.onPrimaryContainer,
                  fontWeight = FontWeight.Bold,
                  modifier = Modifier.requiredWidth(IntrinsicSize.Max),
                )
              },
              colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
              ),
              navigationIcon = {
                IconButton(
                  onClick = {
                    scope.launch {
                      val message = when {
                        dynamicColors.value -> "Enabling Dynamic Colors"
                        else -> "Disabling Dynamic Colors"
                      }
                      snackbarHostState.showSnackbar(
                        message = message,
                        withDismissAction = true,
                        actionLabel = "Dismiss",
                        duration = SnackbarDuration.Short,
                      )
                    }
                    dynamicColors.value = !dynamicColors.value
                  },
                  colors = IconButtonDefaults.iconButtonColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                  )
                ) {
                  Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Toggle Dynamic Colors",
                  )
                }
              },
            )
          },
        ) { paddingValues: PaddingValues ->
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
