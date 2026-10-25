package com.yinzcam.fnc314.hiring.core.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
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

private fun Modifier.bottomBorder(strokeWidth: Dp, color: Color) = this.drawBehind {
  // 1. Convert Dp thickness to raw pixels
  val strokeWidthPx = strokeWidth.toPx()

  // 2. Calculate the vertical position for the line
  val y = size.height - strokeWidthPx / 2

  // 3. Draw a line from the bottom-left corner to the bottom-right corner
  drawLine(
    color = color,
    start = Offset(x = 0f, y = y),
    end = Offset(x = size.width, y = y),
    strokeWidth = strokeWidthPx
  )
}

private val Modifier.styled: Modifier
  @Composable
  get() = defaultMinSize(minHeight = 200.dp)
    .fillMaxWidth()
    .background(MaterialTheme.colorScheme.surface)
    .padding(horizontal = 10.dp)
    .bottomBorder(1.dp, MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.3f))

@Composable
public fun ScheduleItem(
  thisTeam: NFLTeam,
  model: ScheduleItemUIDataModel,
  modifier: Modifier = Modifier,
) {
  when (model) {
    is ScheduleItemUIDataModel.ByeWeek -> Row(
      modifier = modifier.styled,
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
      modifier = modifier.styled,
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
    verticalArrangement = Arrangement.SpaceBetween,
  ) {
    Row(
      modifier = Modifier.fillMaxWidth().fillMaxHeight(0.3F),
      horizontalArrangement = Arrangement.SpaceBetween,
    ) {
      Text(
        text = when {
          game.isHome -> thisTeam.teamName.name
          else -> game.gameOpponent.teamName.name
        },
        modifier = Modifier.fillMaxWidth(0.5F),
        style = MaterialTheme.typography.headlineMedium,
        color = MaterialTheme.colorScheme.onSurface,
        textAlign = TextAlign.Center,
      )
      Text(
        text = when {
          game.isHome -> game.gameOpponent.teamName.name
          else -> thisTeam.teamName.name
        },
        modifier = Modifier.fillMaxWidth(0.5F),
        style = MaterialTheme.typography.headlineMedium,
        color = MaterialTheme.colorScheme.onSurface,
        textAlign = TextAlign.Center,
      )
    }
    Row(
      modifier = Modifier.fillMaxWidth().fillMaxHeight(0.5F),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically,
    ) {
      Text(
        text = when {
          game.isHome -> "${game.gameScore.thisTeam.points}"
          else -> "${game.gameScore.opponent.points}"
        },
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth(0.25F),
        style = MaterialTheme.typography.displayMedium,
        color = MaterialTheme.colorScheme.onSurface,
      )
      Row(
        modifier = Modifier.fillMaxHeight().fillMaxWidth(0.5F),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
      ) {
        TeamLogo(
          model = when {
            game.isHome -> thisTeam.teamLogoData
            else -> game.gameOpponent.teamLogoData
          },
        )
        Text(
          "@",
          style = MaterialTheme.typography.titleLarge,
          color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        TeamLogo(
          model = when {
            game.isHome -> game.gameOpponent.teamLogoData
            else -> thisTeam.teamLogoData
          },
        )
      }
      Text(
        text = when {
          game.isHome -> "${game.gameScore.opponent.points}"
          else -> "${game.gameScore.thisTeam.points}"
        },
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth(0.25F),
        style = MaterialTheme.typography.displayMedium,
        color = MaterialTheme.colorScheme.onSurface,
      )
    }
    Row(
      modifier = Modifier.fillMaxWidth().fillMaxHeight(0.2F),
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
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.onSurface,
        fontWeight = FontWeight.Bold
      )
      Text(
        text = game.gameDate.week,
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
      )
      if (game.gameState == GameState.FINISHED) {
        Text(
          text = "Final",
          style = MaterialTheme.typography.titleMedium,
          color = MaterialTheme.colorScheme.onSurface,
          fontWeight = FontWeight.Bold
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
          style = MaterialTheme.typography.titleMedium,
          color = MaterialTheme.colorScheme.onSurface,
          fontWeight = FontWeight.Bold
        )
      }
    }
  }
}