package com.yinzcam.fnc314.hiring.core.modifiers

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

public fun Modifier.bottomBorder(strokeWidth: Dp, color: Color): Modifier = drawBehind {
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