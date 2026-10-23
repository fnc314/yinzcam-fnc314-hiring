package com.yinzcam.fnc314.hiring.feature.teamschedule.domain

import com.yinzcam.fnc314.hiring.core.data.NFLTeams
import com.yinzcam.fnc314.hiring.feature.teamschedule.data.TeamScheduleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow

public fun interface LoadTeamScheduleUseCase {
  operator fun invoke(team: NFLTeams): Flow<LoadTeamScheduleResult>

  public companion object {
    public fun newInstance(
      repository: TeamScheduleRepository = TeamScheduleRepository.create(
        dispatcher = Dispatchers.IO,
      )
    ): LoadTeamScheduleUseCase = LoadTeamScheduleUseCaseImpl(repository = repository)
  }
}