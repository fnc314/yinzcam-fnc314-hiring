package com.yinzcam.fnc314.hiring.core.data.ui

import com.yinzcam.fnc314.hiring.core.data.Date
import com.yinzcam.fnc314.hiring.core.data.NFLTeamsTriCode
import kotlinx.serialization.Serializable

@Serializable
public sealed class ScheduleItemUIDataModel {
  public abstract val scheduleItemId: String
  public abstract val gameSeason: GameSeason
  @Serializable
  public data class ByeWeek(
    override val scheduleItemId: String,
    override val gameSeason: GameSeason,
  ) : ScheduleItemUIDataModel()

  @Serializable
  public sealed class Games(
    override val scheduleItemId: String,
    override val gameSeason: GameSeason,
    public open val gameState: GameState,
    public open val gameOpponent: NFLTeam,
    public open val gameScore: MatchScore,
    public open val gameDate: GameDate,
  ): ScheduleItemUIDataModel() {
    public val isHome: Boolean get() =
      (this is RegularHome).or(this is PostHome)

    public val isRegularSeason: Boolean get() =
      gameSeason == GameSeason.REGULAR

    public data class Preseason(
      override val scheduleItemId: String,
      override val gameState: GameState,
      override val gameOpponent: NFLTeam,
      override val gameScore: MatchScore,
      override val gameDate: GameDate,
    ) : Games(
      scheduleItemId = scheduleItemId,
      gameSeason = GameSeason.PRE,
      gameState = gameState,
      gameOpponent = gameOpponent,
      gameScore = gameScore,
      gameDate = gameDate,
    )
    public data class RegularHome(
      override val scheduleItemId: String,
      override val gameState: GameState,
      override val gameOpponent: NFLTeam,
      override val gameScore: MatchScore,
      override val gameDate: GameDate,
    ) : Games(
      scheduleItemId = scheduleItemId,
      gameSeason = GameSeason.REGULAR,
      gameState = gameState,
      gameOpponent = gameOpponent,
      gameScore = gameScore,
      gameDate = gameDate,
    )
    public data class RegularAway(
      override val scheduleItemId: String,
      override val gameState: GameState,
      override val gameOpponent: NFLTeam,
      override val gameScore: MatchScore,
      override val gameDate: GameDate,
    ) : Games(
      scheduleItemId = scheduleItemId,
      gameSeason = GameSeason.REGULAR,
      gameState = gameState,
      gameOpponent = gameOpponent,
      gameScore = gameScore,
      gameDate = gameDate,
    )
    public data class PostHome(
      override val scheduleItemId: String,
      override val gameState: GameState,
      override val gameOpponent: NFLTeam,
      override val gameScore: MatchScore,
      override val gameDate: GameDate,
    ) : Games(
      scheduleItemId = scheduleItemId,
      gameSeason = GameSeason.POST,
      gameState = gameState,
      gameOpponent = gameOpponent,
      gameScore = gameScore,
      gameDate = gameDate,
    )

    public data class PostAway(
      override val scheduleItemId: String,
      override val gameState: GameState,
      override val gameOpponent: NFLTeam,
      override val gameScore: MatchScore,
      override val gameDate: GameDate,
    ) : Games(
      scheduleItemId = scheduleItemId,
      gameSeason = GameSeason.POST,
      gameState = gameState,
      gameOpponent = gameOpponent,
      gameScore = gameScore,
      gameDate = gameDate,
    )
  }

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
public enum class GameSeason {
  PRE,
  REGULAR,
  POST,
  ;

  public fun label(year: Int): String =
    "$year $name SEASON"
}