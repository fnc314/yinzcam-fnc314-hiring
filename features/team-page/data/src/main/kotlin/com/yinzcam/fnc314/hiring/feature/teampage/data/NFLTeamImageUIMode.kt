package com.yinzcam.fnc314.hiring.feature.teampage.data

import com.yinzcam.fnc314.hiring.core.data.NFLTeams

enum class NFLTeamImageUIMode(
  internal val imageUrl: String
) {
  LIGHT(imageUrl = "_light.png"),
  DARK(imageUrl = "_dark.png"),
  ;

  fun urlForTeam(nflTeam: NFLTeams): String =
    "nfl_${nflTeam.triCode.lowercase()}${imageUrl}"
}