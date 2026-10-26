package com.yinzcam.fnc314.hiring.core.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.yinzcam.fnc314.hiring.core.data.ui.GameState
import com.yinzcam.fnc314.hiring.core.data.ui.NFLTeam
import com.yinzcam.fnc314.hiring.core.data.ui.ScheduleItemUIDataModel
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format.DayOfWeekNames
import kotlinx.datetime.format.MonthNames
import kotlinx.datetime.format.char
import kotlinx.datetime.toLocalDateTime

@Composable
public fun ScheduleItem(
  thisTeam: NFLTeam,
  model: ScheduleItemUIDataModel,
  modifier: Modifier = Modifier,
) {
  when (model) {
    is ScheduleItemUIDataModel.ByeWeek -> Row(
      modifier = modifier,
      horizontalArrangement = Arrangement.Center,
      verticalAlignment = Alignment.CenterVertically,
    ) {
      Text(
        text = "BYE",
        style = MaterialTheme.typography.displayLarge,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
      )
    }
    is ScheduleItemUIDataModel.Game -> ScheduledMatch(
      thisTeam = thisTeam,
      game = model,
      modifier = modifier,
    )

  }
}

@Composable
private fun ScheduledMatch(
  thisTeam: NFLTeam,
  game: ScheduleItemUIDataModel.Game,
  modifier: Modifier = Modifier,
) {
  Column(
    modifier = modifier,
    verticalArrangement = Arrangement.spacedBy(
      space = 8.dp
    ),
  ) {
    TeamNames(
      thisTeam = thisTeam,
      game = game,
      modifier = Modifier.fillMaxWidth().weight(1F),
    )
    ScoresAndLogos(
      thisTeam = thisTeam,
      game = game,
      modifier = Modifier.fillMaxWidth().weight(2F)
    )
    GameDateTime(
      thisTeam = thisTeam,
      game = game,
      modifier = Modifier.fillMaxWidth().weight(1F)
    )
  }
}

@Composable
private fun TeamNames(
  thisTeam: NFLTeam,
  game: ScheduleItemUIDataModel.Game,
  modifier: Modifier = Modifier,
) {
  Row(
    modifier = modifier,
    horizontalArrangement = Arrangement.SpaceAround,
    verticalAlignment = Alignment.CenterVertically,
  ) {
    Text(
      text = when {
        game.isHome -> thisTeam.teamName.name
        else -> game.gameOpponent.teamName.name
      },
      modifier = Modifier.weight(1F),
      style = MaterialTheme.typography.headlineMedium,
      color = MaterialTheme.colorScheme.onSurface,
      textAlign = TextAlign.Start,
    )
    Text(
      text = when {
        game.isHome -> game.gameOpponent.teamName.name
        else -> thisTeam.teamName.name
      },
      modifier = Modifier.weight(1f),
      style = MaterialTheme.typography.headlineMedium,
      color = MaterialTheme.colorScheme.onSurface,
      textAlign = TextAlign.End,
    )
  }
}

@Composable
private fun ScoresAndLogos(
  thisTeam: NFLTeam,
  game: ScheduleItemUIDataModel.Game,
  modifier: Modifier = Modifier,
) {
  Row(
    modifier = modifier,
    horizontalArrangement = Arrangement.SpaceAround,
    verticalAlignment = Alignment.CenterVertically,
  ) {
    Text(
      text = when {
        game.isHome -> "${game.gameScore.thisTeam.points}"
        else -> "${game.gameScore.opponent.points}"
      },
      textAlign = TextAlign.Start,
      modifier = Modifier.weight(1F),
      style = MaterialTheme.typography.displayMedium,
      color = MaterialTheme.colorScheme.onSurface,
    )
    TeamLogos(
      thisTeam = thisTeam,
      game = game,
      modifier = Modifier.weight(2F),
    )
    Text(
      text = when {
        game.isHome -> "${game.gameScore.opponent.points}"
        else -> "${game.gameScore.thisTeam.points}"
      },
      textAlign = TextAlign.End,
      modifier = Modifier.weight(1F),
      style = MaterialTheme.typography.displayMedium,
      color = MaterialTheme.colorScheme.onSurface,
    )
  }
}

@Composable
private fun TeamLogos(
  thisTeam: NFLTeam,
  game: ScheduleItemUIDataModel.Game,
  modifier: Modifier = Modifier,
) {
  Row(
    modifier = modifier.fillMaxHeight(),
    horizontalArrangement = Arrangement.Center,
    verticalAlignment = Alignment.CenterVertically,
  ) {
    TeamLogo(
      model = when {
        game.isHome -> thisTeam.teamLogoData
        else -> game.gameOpponent.teamLogoData
      },
      modifier = Modifier.weight(2F).fillMaxHeight(),
    )
    Text(
      "@",
      style = MaterialTheme.typography.titleLarge,
      color = MaterialTheme.colorScheme.onSurfaceVariant,
      textAlign = TextAlign.Center,
      modifier = Modifier.weight(1F),
    )
    TeamLogo(
      model = when {
        game.isHome -> game.gameOpponent.teamLogoData
        else -> thisTeam.teamLogoData
      },
      modifier = Modifier.weight(2F).fillMaxHeight(),
    )
  }
}

@Composable
private fun GameDateTime(
  thisTeam: NFLTeam,
  game: ScheduleItemUIDataModel.Game,
  modifier: Modifier = Modifier,
) {
  Row(
    modifier = modifier,
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically,
  ) {
    Text(
      text = LocalDateTime.Format {
        dayOfWeek(DayOfWeekNames.ENGLISH_ABBREVIATED)
        char(',')
        char(' ')
        monthName(MonthNames.ENGLISH_ABBREVIATED)
        char(' ')
        day()
      }.format(
        game
          .gameDate
          .date
          .timestamp
          .toLocalDateTime(TimeZone.currentSystemDefault())
      ),
      modifier = Modifier.weight(1F),
      style = MaterialTheme.typography.titleMedium,
      color = MaterialTheme.colorScheme.onSurface,
      fontWeight = FontWeight.Bold,
      textAlign = TextAlign.Start,
    )
    Text(
      modifier = Modifier.weight(1F),
      text = game.gameDate.week,
      style = MaterialTheme.typography.titleMedium,
      color = MaterialTheme.colorScheme.onSurfaceVariant,
      textAlign = TextAlign.Center,
    )
    if (game.gameState == GameState.FINISHED) {
      Text(
        text = "Final",
        modifier = Modifier.weight(1F),
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.onSurface,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.End,
      )
    } else {
      Text(
        text = LocalDateTime.Format {
          hour()
          char(':')
          minute()
          char(' ')
          amPmMarker(am = "AM", pm = "PM")
        }.format(
          game
            .gameDate
            .date
            .timestamp
            .toLocalDateTime(TimeZone.currentSystemDefault())
        ),
        textAlign = TextAlign.End,
        modifier = Modifier.weight(1F),
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.onSurface,
        fontWeight = FontWeight.Bold
      )
    }
  }
}