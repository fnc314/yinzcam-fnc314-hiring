package com.yinzcam.fnc314.hiring.feature.teamschedule.domain

import androidx.lifecycle.viewmodel.CreationExtras
import com.yinzcam.fnc314.hiring.core.data.NFLTeamsTriCode
import com.yinzcam.fnc314.hiring.feature.teamschedule.data.TeamScheduleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow

public fun interface LoadTeamScheduleUseCase {
  public operator fun invoke(team: NFLTeamsTriCode): Flow<LoadTeamScheduleResult>

  public companion object : CreationExtras.Key<LoadTeamScheduleUseCase> {
    public fun newInstance(
      repository: TeamScheduleRepository = TeamScheduleRepository.create(
        dispatcher = Dispatchers.IO,
      )
    ): LoadTeamScheduleUseCase = LoadTeamScheduleUseCaseImpl(repository = repository)
  }
}