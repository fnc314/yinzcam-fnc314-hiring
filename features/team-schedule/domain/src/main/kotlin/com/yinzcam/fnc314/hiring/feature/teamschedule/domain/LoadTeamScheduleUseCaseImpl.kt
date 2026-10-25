package com.yinzcam.fnc314.hiring.feature.teamschedule.domain

import com.yinzcam.fnc314.hiring.core.data.NFLTeamsTriCode
import com.yinzcam.fnc314.hiring.feature.teamschedule.data.TeamScheduleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class LoadTeamScheduleUseCaseImpl(
  private val repository: TeamScheduleRepository,
) : LoadTeamScheduleUseCase {
  override fun invoke(team: NFLTeamsTriCode): Flow<LoadTeamScheduleResult> = flow {
    emit(LoadTeamScheduleResult.IsLoading)
    repository.loadTeamPage(team = team).let {
      LoadTeamScheduleResult.Success(
        teamPage = it
      )
    }.also {
      emit(it)
    }
  }
}