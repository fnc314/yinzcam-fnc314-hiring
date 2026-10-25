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
import com.yinzcam.fnc314.hiring.core.data.NFLTeamsTriCode

private const val TEAM_LOGO_URL: String = "https://s3.amazonaws.com/yc-app-resources/nfl/logos/"
data class TeamLogoData(
  val team: NFLTeamsTriCode
) {
  fun imageUrl(
    mode: TeamLogoMode,
  ): String =
    "$TEAM_LOGO_URL/${mode.urlForTeam(nflTeam = team)}"
}

@Composable
fun TeamLogo(
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

enum class TeamLogoMode(
  internal val imageUrl: String
) {
  LIGHT(imageUrl = "_light.png"),
  DARK(imageUrl = "_dark.png"),
  ;

  fun urlForTeam(nflTeam: NFLTeamsTriCode): String =
    "nfl_${nflTeam.triCode.lowercase()}${imageUrl}"
}