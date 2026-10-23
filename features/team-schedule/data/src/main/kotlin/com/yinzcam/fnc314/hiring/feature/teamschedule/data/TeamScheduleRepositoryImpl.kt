package com.yinzcam.fnc314.hiring.feature.teamschedule.data

import com.yinzcam.fnc314.hiring.core.data.NFLTeams
import com.yinzcam.fnc314.hiring.core.data.NFLTeamsPage
import com.yinzcam.fnc314.hiring.core.data.Team
import com.yinzcam.fnc314.hiring.core.data.YinzNode
import com.yinzcam.fnc314.hiring.core.network.NetworkResponse
import com.yinzcam.fnc314.hiring.core.network.NetworkService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.Request

internal class TeamScheduleRepositoryImpl(
  private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
  private val networkService: NetworkService = NetworkService.DEFAULT,
) : TeamScheduleRepository {
  companion object {
    private const val IMAGE_URL: String =
      "https://s3.amazonaws.com/yc-app-resources/nfl/logos/"
  }
  override suspend fun loadTeamPage(team: NFLTeams): NFLTeamsPage =
    withContext(dispatcher) {
      networkService(
        request = Request.Builder()
          .url("https://files.yinzcam.com.s3.amazonaws.com/iOS/interviews/ScheduleExercise/schedule.json")
          .build(),
        deserializer = NFLTeamsPage.serializer()
      ).let { response: NetworkResponse<NFLTeamsPage, Any> ->
        when (response) {
          is NetworkResponse.Success -> response.data
          else -> NFLTeamsPage(
            defaultGameID = Long.MIN_VALUE,
            filters = emptyList(),
            gameSection = emptyList(),
            team = Team(
              city = "",
              fullName = "",
              losses = "",
              name = "",
              primaryColor = "",
              record = "",
              triCode = "",
              winPercentage = "",
              wins = "",
            ),
            yinzNode = YinzNode(
              ads = "",
              carrier = "",
              generated = "",
              inVenue = false,
              venueStatus = "",
            )
          )
        }
      }
    }

  override fun teamImageUrl(team: NFLTeams, mode: NFLTeamImageUIMode): String =
    "$IMAGE_URL/${mode.urlForTeam(nflTeam = team)}"
}