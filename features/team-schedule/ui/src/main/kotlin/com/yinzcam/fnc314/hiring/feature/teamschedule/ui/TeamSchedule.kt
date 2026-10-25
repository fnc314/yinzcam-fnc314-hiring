package com.yinzcam.fnc314.hiring.feature.teamschedule.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
            Text(
              text = gSection.heading,
              style = MaterialTheme.typography.titleMedium,
              modifier = modifier.fillMaxWidth()
            )
          }
          items(
            count = gSection.game.size,
            key = { "${gSection.heading}-$it" },
          ) { indexInner ->
            Row(
              modifier = modifier
                .defaultMinSize(minHeight = 200.dp)
                .fillMaxWidth()
                .padding(horizontal = 5.dp)
                .background(MaterialTheme.colorScheme.surface)
            ) {
              Text(
                text = gSection.game[indexInner].scheduleHeader,
                style = MaterialTheme.typography.headlineMedium,
              )
            }
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