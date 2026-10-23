package com.yinzcam.fnc314.hiring.core.data

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName
import kotlinx.serialization.json.*

@Serializable
data class NFLTeamsPage(
  @SerialName("DefaultGameId")
  val defaultGameID: Long,

  @SerialName("Filters")
  val filters: List<Filter>,

  @SerialName("GameSection")
  val gameSection: List<GameSection>,

  @SerialName("Team")
  val team: Team,

  @SerialName("YinzNode")
  val yinzNode: YinzNode
)
