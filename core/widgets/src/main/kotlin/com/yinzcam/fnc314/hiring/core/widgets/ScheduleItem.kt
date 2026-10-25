package com.yinzcam.fnc314.hiring.core.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.yinzcam.fnc314.hiring.core.data.Game
import com.yinzcam.fnc314.hiring.core.data.ui.ScheduleItemUIDataModel

private val Modifier.styled: Modifier
  @Composable
  get() = defaultMinSize(minHeight = 200.dp)
    .fillMaxHeight()
    .background(MaterialTheme.colorScheme.surface)

@Composable
fun ScheduleItem(
  model: ScheduleItemUIDataModel,
  modifier: Modifier = Modifier,
) {
  when (model) {
    ScheduleItemUIDataModel.ByeWeek -> Row(
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
    is ScheduleItemUIDataModel.Match -> Box(
      modifier = modifier.styled,
    ) {

    }
  }
}