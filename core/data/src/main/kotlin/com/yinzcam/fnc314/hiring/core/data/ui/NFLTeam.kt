package com.yinzcam.fnc314.hiring.core.data.ui

import com.yinzcam.fnc314.hiring.core.data.NFLTeamsTriCode
import kotlinx.serialization.Serializable

@Serializable
public data class NFLTeam(
  public val triCode: NFLTeamsTriCode,
  public val teamName: TeamName,
  public val teamRecord: TeamRecord,
  public val teamLogoData: TeamLogoData,
) {
}