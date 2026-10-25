package com.yinzcam.fnc314.hiring.core.data.ui

import com.yinzcam.fnc314.hiring.core.data.NFLTeamsTriCode
import kotlinx.serialization.Serializable

private const val TEAM_LOGO_URL: String = "https://s3.amazonaws.com/yc-app-resources/nfl/logos/"

@Serializable
public data class TeamLogoData(
  public val team: NFLTeamsTriCode
) {
  public fun imageUrl(
    mode: TeamLogoMode,
  ): String =
    "$TEAM_LOGO_URL/${mode.urlForTeam(nflTeam = team)}"
}

public enum class TeamLogoMode(
  internal val imageUrl: String
) {
  LIGHT(imageUrl = "_light.png"),
  DARK(imageUrl = "_dark.png"),
  ;

  public fun urlForTeam(nflTeam: NFLTeamsTriCode): String =
    "nfl_${nflTeam.triCode.lowercase()}${imageUrl}"
}