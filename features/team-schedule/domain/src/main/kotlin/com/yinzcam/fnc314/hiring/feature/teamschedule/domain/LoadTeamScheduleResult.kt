package com.yinzcam.fnc314.hiring.feature.teamschedule.domain

import com.yinzcam.fnc314.hiring.core.data.GameType
import com.yinzcam.fnc314.hiring.core.data.NFLTeamsPage
import com.yinzcam.fnc314.hiring.core.data.ui.GameSeason
import com.yinzcam.fnc314.hiring.core.data.ui.GameState
import com.yinzcam.fnc314.hiring.core.data.ui.MatchScore
import com.yinzcam.fnc314.hiring.core.data.ui.NFLTeam
import com.yinzcam.fnc314.hiring.core.data.ui.Points
import com.yinzcam.fnc314.hiring.core.data.ui.ScheduleItemUIDataModel
import com.yinzcam.fnc314.hiring.core.data.ui.TeamLogoData
import com.yinzcam.fnc314.hiring.core.data.ui.TeamName
import com.yinzcam.fnc314.hiring.core.data.ui.TeamRecord
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.todayIn
import kotlin.time.Clock

public sealed class LoadTeamScheduleResult {
  public data object None : LoadTeamScheduleResult()
  public data object IsLoading : LoadTeamScheduleResult()
  public data class Success(
    val teamPage: NFLTeamsPage
  ): LoadTeamScheduleResult() {
    public val thisTeam: NFLTeam = teamPage.team.toNFLTeam()
    public val models: List<ScheduleItemUIDataModel> =
      teamPage.gameSection.flatMap {
        it.game.map { g ->
          when (val opponent = g.opponent) {
            null -> ScheduleItemUIDataModel.ByeWeek(
              scheduleItemId = g.scheduleHeader,
            )
            else -> ScheduleItemUIDataModel.Match(
              isHome = g.isHome,
              scheduleItemId = g.scheduleHeader,
              gameState = when (g.type) {
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
              },
              gameSeason = if (it.heading.lowercase().contains("regular")) {
                GameSeason.SEASON
              } else {
                GameSeason.POST
              },
              gameOpponent = NFLTeam(
                teamLogoData = TeamLogoData(team = opponent.triCode),
                triCode = opponent.triCode,
                teamName = TeamName(
                  name = opponent.name,
                  city = opponent.city,
                  fullName = opponent.fullName,
                ),
                teamRecord = TeamRecord.parse(opponent.record)
              ),
              gameScore = MatchScore(
                thisTeam = when {
                  g.isHome -> Points(points = g.homeScore.orEmpty().toInt())
                  else -> Points(points = g.awayScore.orEmpty().toInt())
                },
                opponent = when {
                  g.isHome -> Points(points = g.awayScore.orEmpty().toInt())
                  else -> Points(points = g.homeScore.orEmpty().toInt())
                }
              )
            )
          }
        }
      }
  }
  public sealed class Error : LoadTeamScheduleResult() {
    public data object NoData : Error()
    public data object NoNetwork : Error()
    public data object Unknown : Error()
  }
}
