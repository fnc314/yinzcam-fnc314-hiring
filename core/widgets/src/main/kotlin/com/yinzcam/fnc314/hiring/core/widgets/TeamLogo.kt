package com.yinzcam.fnc314.hiring.core.widgets

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.yinzcam.fnc314.hiring.core.data.ui.TeamLogoData
import com.yinzcam.fnc314.hiring.core.data.ui.TeamLogoMode


@Composable
public fun TeamLogo(
  model: TeamLogoData,
  modifier: Modifier = Modifier
) {
  AsyncImage(
    contentDescription = "Logo for ${model.team.triCode}",
    model = ImageRequest.Builder(
      context = LocalContext.current,
    )
      .crossfade(enable = true)
      .scale(scale = Scale.FIT)
      .placeholder(com.yinzcam.fnc314.hiring.core.designsystem.R.drawable.ic_launcher_background)
      .data(
        data = model.imageUrl(
          mode = if (isSystemInDarkTheme()) {
            TeamLogoMode.DARK
          } else {
            TeamLogoMode.LIGHT
          }
        )
      )
      .build(),
    alignment = Alignment.Center,
    modifier = modifier
      .size(20.dp),
  )
}