package com.yinzcam.fnc314.hiring.feature.teampage.domain

import com.yinzcam.fnc314.hiring.core.data.NFLTeams
import com.yinzcam.fnc314.hiring.feature.teampage.data.TeamPageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow

public fun interface LoadTeamPageUseCase {
  operator fun invoke(team: NFLTeams): Flow<LoadTeamPageResult>

  public companion object {
    public fun newInstance(
      repository: TeamPageRepository = TeamPageRepository.create(
        dispatcher = Dispatchers.IO,
      )
    ): LoadTeamPageUseCase = LoadTeamPageUseCaseImpl(repository = repository)
  }
}