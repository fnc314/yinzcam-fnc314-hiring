package com.yinzcam.fnc314.hiring.core.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.yinzcam.fnc314.hiring.core.data.ui.NFLTeam
import com.yinzcam.fnc314.hiring.core.data.ui.ScheduleItemUIDataModel

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
    .fillMaxHeight()
    .background(MaterialTheme.colorScheme.surface)
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
    is ScheduleItemUIDataModel.Match -> ScheduledMatch(
      thisTeam = thisTeam,
      match = model,
      modifier = modifier.styled,
    )

  }
}

@Composable
private fun ScheduledMatch(
  thisTeam: NFLTeam,
  match: ScheduleItemUIDataModel.Match,
  modifier: Modifier = Modifier,
) {
  Column(
    modifier = modifier,
  ) {
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween,
    ) {
      Text(
        text = thisTeam.teamName.name,
        style = MaterialTheme.typography.headlineMedium,
        color = MaterialTheme.colorScheme.onSurface,
      )
      Text(
        text = match.gameOpponent.teamName.name,
        style = MaterialTheme.typography.headlineMedium,
        color = MaterialTheme.colorScheme.onSurface,
      )
    }
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween,
    ) {
      Text(
        text = "${match.gameScore.thisTeam.points}",
        style = MaterialTheme.typography.displayMedium,
        color = MaterialTheme.colorScheme.onSurface,
      )
      Row(
        modifier = Modifier,
        horizontalArrangement = Arrangement.Center,
      ) {
        TeamLogo(
          model = thisTeam.teamLogoData,
        )
        Text(
          "@",
          style = MaterialTheme.typography.displayLarge,
          color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        TeamLogo(
          model = match.gameOpponent.teamLogoData,
        )
      }
      Text(
        text = "${match.gameScore.opponent.points}",
        style = MaterialTheme.typography.displayMedium,
        color = MaterialTheme.colorScheme.onSurface,
      )
    }
  }
}