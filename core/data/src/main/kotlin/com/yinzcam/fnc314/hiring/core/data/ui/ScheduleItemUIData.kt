package com.yinzcam.fnc314.hiring.core.data.ui

import com.yinzcam.fnc314.hiring.core.data.Date
import com.yinzcam.fnc314.hiring.core.data.NFLTeamsTriCode
import kotlinx.serialization.Serializable

@Serializable
public sealed class ScheduleItemUIDataModel {
  public abstract val scheduleItemId: String
  @Serializable
  public data class ByeWeek(override val scheduleItemId: String) : ScheduleItemUIDataModel()

  @Serializable
  public data class Game(
    override val scheduleItemId: String,
    public val isHome: Boolean,
    public val gameState: GameState,
    public val gameSeason: GameSeason,
    public val gameOpponent: NFLTeam,
    public val gameScore: MatchScore,
    public val gameDate: GameDate,
  ) : ScheduleItemUIDataModel()
}

@Serializable
public data class GameDate(
  public val date: Date,
  public val week: String,
)

@JvmInline
@Serializable
public value class Points(public val points: Int)

@Serializable
public data class MatchScore(
  public val thisTeam: Points,
  public val opponent: Points,
)

@Serializable
public data class TeamData(
  val triCode: NFLTeamsTriCode,
  val teamName: TeamName,
  val teamRecord: TeamRecord,
)

@Serializable
public data class TeamName(
  val name: String,
  val fullName: String,
  val city: String,
)

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
  PLAYING,
  FINISHED,
  ;
}

@Serializable
public enum class GameSeason { PRE, SEASON, POST, }