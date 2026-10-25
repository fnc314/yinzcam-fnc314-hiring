package com.yinzcam.fnc314.hiring.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.yinzcam.fnc314.hiring.core.data.NFLTeamsTriCode
import com.yinzcam.fnc314.hiring.data.DefaultDataRepository
import com.yinzcam.fnc314.hiring.core.navigation.NavDestinations
import com.yinzcam.fnc314.hiring.core.designsystem.theme.FNC314HiringTheme

@Composable
fun MainScreen(
  onItemClick: (NavDestinations) -> Unit,
  modifier: Modifier = Modifier,
  navController: NavController,
  viewModel: MainScreenViewModel = viewModel { MainScreenViewModel(DefaultDataRepository()) },
) {
  val state by viewModel.uiState.collectAsStateWithLifecycle()
  when (state) {
    MainScreenUiState.Loading -> {
      // Blank
    }
    is MainScreenUiState.Success -> {
      MainScreenList(
        data = (state as MainScreenUiState.Success).data,
        modifier = modifier.clickable {
          navController.navigate(NavDestinations.TeamSchedule(team = NFLTeamsTriCode.GB))
        },
      )
    }
    is MainScreenUiState.Error -> {
      Text("Error loading data: ${(state as MainScreenUiState.Error).throwable.message}")
    }
  }
}

@Composable
internal fun MainScreenList(data: List<String>, modifier: Modifier = Modifier) {
  Column(modifier) { data.forEach { Greeting(it) } }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
  Text(
    text = "Hello $name!",
    textAlign = TextAlign.Center,
    color = MaterialTheme.colorScheme.onSurface,
    modifier = modifier.fillMaxWidth().fillMaxHeight().background(MaterialTheme.colorScheme.primary)
  )
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
  FNC314HiringTheme { MainScreenList(listOf("Android")) }
}

@Preview(showBackground = true, widthDp = 340)
@Composable
fun MainScreenPortraitPreview() {
  FNC314HiringTheme { MainScreenList(listOf("Android")) }
}
