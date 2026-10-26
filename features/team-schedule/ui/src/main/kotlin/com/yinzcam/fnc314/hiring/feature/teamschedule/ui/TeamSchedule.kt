package com.yinzcam.fnc314.hiring.feature.teamschedule.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.progressSemantics
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.CollectionInfo
import androidx.compose.ui.semantics.CollectionItemInfo
import androidx.compose.ui.semantics.collectionInfo
import androidx.compose.ui.semantics.collectionItemInfo
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.yinzcam.fnc314.hiring.core.data.ui.GameSeason
import com.yinzcam.fnc314.hiring.core.data.ui.ScheduleItemUIDataModel
import com.yinzcam.fnc314.hiring.core.widgets.ScheduleItem
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@OptIn(ExperimentalFoundationApi::class)
@Composable
public fun TeamSchedule(
  viewModel: TeamScheduleViewModel,
  modifier: Modifier = Modifier,
) {
  val pageState = viewModel.state.collectAsStateWithLifecycle()
  when (val stateData = pageState.value) {
    TeamScheduleUIState.Initial,
    is TeamScheduleUIState.IsBusy -> {
      CircularProgressIndicator(
        modifier = Modifier.fillMaxSize().progressSemantics(),
        color = MaterialTheme.colorScheme.onPrimary,
        strokeWidth = 10.dp,
        trackColor = MaterialTheme.colorScheme.primary,
      )
    }
    is TeamScheduleUIState.Loaded -> {
      val year = remember(stateData.data.thisTeam) {
        stateData.data.models[GameSeason.REGULAR]!!
          .filterIsInstance<ScheduleItemUIDataModel.Games>()
          .first()
          .gameDate
          .date
          .timestamp
          .toLocalDateTime(timeZone = TimeZone.currentSystemDefault())
          .year
      }
      LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 5.dp),
      ) {
        stateData.data.models.forEach { (season, games) ->
          stickyHeader(key = season) {
            Row(
              modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .padding(vertical = 4.dp)
                .background(MaterialTheme.colorScheme.surfaceContainerHigh)
                .semantics(mergeDescendants = true) {
                  heading()
                  collectionInfo = CollectionInfo(
                    rowCount = games.size,
                    columnCount = 1,
                  )
                },
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.Center,
            ) {
              Text(
                text = season.label(year = year),
                modifier = Modifier.padding(vertical = 4.dp),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center,
              )
            }
          }
          items(
            count = games.size,
            key = { games[it].scheduleItemId },
          ) { index ->
              ScheduleItem(
                thisTeam = stateData.data.thisTeam,
                model = games[index],
                modifier = modifier
                  .defaultMinSize(minHeight = 200.dp)
                  .fillMaxWidth()
                  .padding(vertical = 5.dp, horizontal = 10.dp)
                  .background(MaterialTheme.colorScheme.surface)
                  .semantics() {
                    collectionItemInfo = CollectionItemInfo(
                      rowIndex = index,
                      rowSpan = 1,
                      columnIndex = 0,
                      columnSpan = 1
                    )
                  }
              )
              HorizontalDivider(
                modifier = Modifier,
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
              )
            }
          }
        }
    }
    is TeamScheduleUIState.Error -> {
      Text(
        text = "Error ${stateData::class.simpleName}",
        modifier = Modifier.fillMaxSize(),
        style = MaterialTheme.typography.headlineLarge,
        textAlign = TextAlign.Center,
      )
    }
  }
}