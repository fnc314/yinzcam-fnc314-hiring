package com.yinzcam.fnc314.hiring.feature.teamschedule.domain

import com.yinzcam.fnc314.hiring.core.data.NFLTeams
import com.yinzcam.fnc314.hiring.feature.teamschedule.data.TeamScheduleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class LoadTeamScheduleUseCaseImpl(
  private val repository: TeamScheduleRepository,
) : LoadTeamScheduleUseCase {
  override fun invoke(team: NFLTeams): Flow<LoadTeamScheduleResult> = flow {
    emit(LoadTeamScheduleResult.IsLoading)
    repository.loadTeamPage(team = team)
  }
}