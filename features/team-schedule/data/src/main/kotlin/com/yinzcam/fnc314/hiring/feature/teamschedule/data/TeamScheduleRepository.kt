package com.yinzcam.fnc314.hiring.feature.teamschedule.data

import com.yinzcam.fnc314.hiring.core.data.NFLTeamsTriCode
import com.yinzcam.fnc314.hiring.core.data.NFLTeamsPage
import com.yinzcam.fnc314.hiring.core.network.NetworkService
import kotlinx.coroutines.CoroutineDispatcher

public interface TeamScheduleRepository {
  public suspend fun loadTeamPage(team: NFLTeamsTriCode): NFLTeamsPage

  public companion object {
    public fun create(
      dispatcher: CoroutineDispatcher,
      networkService: NetworkService = NetworkService.DEFAULT,
    ): TeamScheduleRepository = TeamScheduleRepositoryImpl(
      dispatcher = dispatcher,
      networkService = networkService,
    )
  }
}