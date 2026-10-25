package com.yinzcam.fnc314.hiring.feature.teamschedule.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
public fun TeamSchedule(
  viewModel: TeamScheduleViewModel,
  modifier: Modifier = Modifier,
) {
  val pageState = viewModel.state.collectAsStateWithLifecycle()

  LazyColumn(
    modifier = modifier,
  ) {
    when (val stateData = pageState.value) {
      is TeamScheduleUIState.Loaded -> {
        stateData.data.teamPage.gameSection.forEach { gSection ->
          item(
            key = gSection.heading,
          ) {
            Text(gSection.heading)
          }
          items(
            count = gSection.game.size,
            key = { "${gSection.heading}-$it" },
          ) { indexInner ->
            Text(text = "${gSection.game[indexInner]}")
          }
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