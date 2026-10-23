package com.yinzcam.fnc314.hiring.core.data

import kotlinx.serialization.Serializable

@Serializable
sealed interface NFLTeams {
  val triCode: String get() = "${this::class.java.simpleName}"
  sealed interface AFC : NFLTeams
  data object PIT : AFC

  sealed interface NFC : NFLTeams
  data object EGL : NFC
  data object GB : NFC
}