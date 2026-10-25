package com.yinzcam.fnc314.hiring.core.data.ui

import kotlinx.serialization.Serializable
import com.yinzcam.fnc314.hiring.core.data.NFLTeamsTriCode

@Serializable
public sealed class ScheduleItemUIDataModel {
  @Serializable
  public data object ByeWeek : ScheduleItemUIDataModel()

  @Serializable
  public data class Match(
    val gameState: GameState,
    val gameSeason: GameSeason,
    val gameOpponent: TeamDataImpl,
  ) : ScheduleItemUIDataModel()
}

@Serializable
public data class TeamDataImpl(
  override val triCode: NFLTeamsTriCode,
  override val teamName: TeamNameImpl,
  override val teamRecord: TeamRecord,
) : TeamData

@Serializable
public data class TeamNameImpl(
  override val name: String,
  override val fullName: String,
  override val city: String,
) : TeamName

public interface TeamData {
  public val triCode: NFLTeamsTriCode
  public val teamName: TeamName
  public val teamRecord: TeamRecord
}

public interface TeamName {
  public val name: String
  public val fullName: String
  public val city: String
}

@Serializable
public data class TeamRecord(
  val wins: Int,
  val losses: Int,
) {
  @kotlinx.serialization.Transient
  val record: String = "$wins-$losses"

  public companion object {
    public fun parse(record: String): TeamRecord = when {
      record.matches("""\d{1,2}-\d{1,2}""".toRegex()) ->
          record.split("-").take(2).let {
            TeamRecord(it.first().toInt(), it.last().toInt())
          }
      else -> TeamRecord(Int.MIN_VALUE, Int.MIN_VALUE)
    }
  }
}

@Serializable
public enum class GameState {
  UPCOMING,
  CURRENTLY_PLAYING,
  FINISHED,
  ;
}

@Serializable
public enum class GameSeason { PRE, SEASON, POST, }