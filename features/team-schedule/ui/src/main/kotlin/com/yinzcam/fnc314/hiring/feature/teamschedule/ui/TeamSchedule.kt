package com.yinzcam.fnc314.hiring.feature.teamschedule.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun TeamSchedule(
  viewModel: TeamScheduleViewModel,
  modifier: Modifier = Modifier,
) {
  val pageState = viewModel.state.collectAsStateWithLifecycle()

  LazyColumn(
    modifier = modifier,
  ) {
    when (val stateData = pageState.value) {
      is TeamScheduleUIState.Loaded -> {
        items(
          count = stateData.data.teamPage.gameSection.size,
          key = { it }
        ) {
          Text(
            stateData.data.teamPage.gameSection[it].heading
          )
        }
      }
      else -> {
        items(
          count = 1,
        ) {
          Text("NOTHING HERE")
        }
      }
    }
  }
}