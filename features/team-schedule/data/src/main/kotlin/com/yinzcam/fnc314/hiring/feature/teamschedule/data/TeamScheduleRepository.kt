package com.yinzcam.fnc314.hiring.feature.teamschedule.data

import com.yinzcam.fnc314.hiring.core.data.NFLTeams
import com.yinzcam.fnc314.hiring.core.data.NFLTeamsPage
import com.yinzcam.fnc314.hiring.core.network.NetworkService
import kotlinx.coroutines.CoroutineDispatcher

public interface TeamScheduleRepository {
  public suspend fun loadTeamPage(team: NFLTeams): NFLTeamsPage

  public fun teamImageUrl(team: NFLTeams, mode: NFLTeamImageUIMode): String

  companion object {
    public fun create(
      dispatcher: CoroutineDispatcher,
      networkService: NetworkService = NetworkService.DEFAULT,
    ): TeamScheduleRepository = TeamScheduleRepositoryImpl(
      dispatcher = dispatcher,
      networkService = networkService,
    )
  }
}