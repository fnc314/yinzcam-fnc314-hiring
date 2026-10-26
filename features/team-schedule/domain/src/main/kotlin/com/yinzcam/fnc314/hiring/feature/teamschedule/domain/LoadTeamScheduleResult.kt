package com.yinzcam.fnc314.hiring.feature.teamschedule.domain

import com.yinzcam.fnc314.hiring.core.data.GameType
import com.yinzcam.fnc314.hiring.core.data.NFLTeamsPage
import com.yinzcam.fnc314.hiring.core.data.ui.GameDate
import com.yinzcam.fnc314.hiring.core.data.ui.GameSeason
import com.yinzcam.fnc314.hiring.core.data.ui.GameState
import com.yinzcam.fnc314.hiring.core.data.ui.MatchScore
import com.yinzcam.fnc314.hiring.core.data.ui.NFLTeam
import com.yinzcam.fnc314.hiring.core.data.ui.Points
import com.yinzcam.fnc314.hiring.core.data.ui.ScheduleItemUIDataModel
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.todayIn
import java.util.EnumMap
import kotlin.time.Clock

public sealed class LoadTeamScheduleResult {
  public data object None : LoadTeamScheduleResult()
  public data object IsLoading : LoadTeamScheduleResult()
  public data class Success(
    private val teamPage: NFLTeamsPage
  ): LoadTeamScheduleResult() {
    public val thisTeam: NFLTeam = teamPage.team.toNFLTeam()

    public val models: EnumMap<GameSeason, List<ScheduleItemUIDataModel>> = teamPage.gameSection
      .groupBy {
        if (it.heading.lowercase().contains("regular")) {
          GameSeason.REGULAR
        } else {
          GameSeason.POST
        }
      }
      .mapValues { (season, games) ->
        games.flatMap { section ->
          section.game.map { g ->
            val opponent = g.opponent
            if (opponent == null) {
              ScheduleItemUIDataModel.ByeWeek(
                scheduleItemId = g.scheduleHeader,
                gameSeason = season,
              )
            } else {
              val gameState = when (g.type) {
                GameType.SCHEDULED -> GameState.UPCOMING
                GameType.FINAL -> GameState.FINISHED
                GameType.BYE -> {
                  val timezone = TimeZone.currentSystemDefault()
                  val gameDate = g.date.timestamp.toLocalDateTime(timezone).date
                  val today = Clock.System.todayIn(timezone)
                  when {
                    gameDate < today -> GameState.FINISHED
                    gameDate > today -> GameState.UPCOMING
                    else -> GameState.PLAYING
                  }
                }
              }
              when (season) {
                GameSeason.REGULAR if (g.isHome) -> ScheduleItemUIDataModel.Games.RegularHome(
                  scheduleItemId = g.scheduleHeader,
                  gameState = gameState,
                  gameOpponent = opponent.toNFLTeam(),
                  gameScore = MatchScore(
                    thisTeam = Points(points = g.homeScore?.toIntOrNull() ?: 0),
                    opponent = Points(points = g.awayScore?.toIntOrNull() ?: 0),
                  ),
                  gameDate = GameDate(
                    date = g.date,
                    week = g.week,
                  )
                )

                GameSeason.REGULAR -> ScheduleItemUIDataModel.Games.RegularAway(
                  scheduleItemId = g.scheduleHeader,
                  gameState = gameState,
                  gameOpponent = opponent.toNFLTeam(),
                  gameScore = MatchScore(
                    thisTeam = Points(points = g.homeScore?.toIntOrNull() ?: 0),
                    opponent = Points(points = g.awayScore?.toIntOrNull() ?: 0),
                  ),
                  gameDate = GameDate(
                    date = g.date,
                    week = g.week,
                  )
                )

                GameSeason.POST if (g.isHome) -> ScheduleItemUIDataModel.Games.PostHome(
                  scheduleItemId = g.scheduleHeader,
                  gameState = gameState,
                  gameOpponent = opponent.toNFLTeam(),
                  gameScore = MatchScore(
                    thisTeam = Points(points = g.homeScore?.toIntOrNull() ?: 0),
                    opponent = Points(points = g.awayScore?.toIntOrNull() ?: 0),
                  ),
                  gameDate = GameDate(
                    date = g.date,
                    week = g.week,
                  )
                )

                GameSeason.POST -> ScheduleItemUIDataModel.Games.PostAway(
                  scheduleItemId = g.scheduleHeader,
                  gameState = gameState,
                  gameOpponent = opponent.toNFLTeam(),
                  gameScore = MatchScore(
                    thisTeam = Points(points = g.homeScore?.toIntOrNull() ?: 0),
                    opponent = Points(points = g.awayScore?.toIntOrNull() ?: 0),
                  ),
                  gameDate = GameDate(
                    date = g.date,
                    week = g.week,
                  )
                )

                GameSeason.PRE -> ScheduleItemUIDataModel.Games.Preseason(
                  scheduleItemId = g.scheduleHeader,
                  gameState = gameState,
                  gameOpponent = opponent.toNFLTeam(),
                  gameScore = MatchScore(
                    thisTeam = Points(points = g.homeScore?.toIntOrNull() ?: 0),
                    opponent = Points(points = g.awayScore?.toIntOrNull() ?: 0),
                  ),
                  gameDate = GameDate(
                    date = g.date,
                    week = g.week,
                  )
                )
              }
            }
          }
        }
      }
      .let { EnumMap(it) }
  }
  public sealed class Error : LoadTeamScheduleResult() {
    public data object NoData : Error()
    public data object NoNetwork : Error()
    public data object Unknown : Error()
  }
}
