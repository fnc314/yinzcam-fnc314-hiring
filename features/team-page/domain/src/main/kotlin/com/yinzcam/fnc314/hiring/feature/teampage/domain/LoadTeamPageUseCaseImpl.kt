package com.yinzcam.fnc314.hiring.feature.teampage.domain

import com.yinzcam.fnc314.hiring.core.data.NFLTeams
import com.yinzcam.fnc314.hiring.feature.teampage.data.TeamPageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class LoadTeamPageUseCaseImpl(
  private val repository: TeamPageRepository,
) : LoadTeamPageUseCase {
  override fun invoke(team: NFLTeams): Flow<LoadTeamPageResult> = flow {
    emit(LoadTeamPageResult.IsLoading)
    repository.loadTeamPage(team = team)
  }
}