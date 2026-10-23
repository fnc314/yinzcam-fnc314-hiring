package com.yinzcam.fnc314.hiring.feature.teampage.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.yinzcam.fnc314.hiring.feature.teampage.domain.LoadTeamPageUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class TeamPageViewModel(
  private val loadTeamPageUseCase: LoadTeamPageUseCase,
) : ViewModel() {

  val state: StateFlow<TeamPageUIState>
    field = MutableStateFlow(value = TeamPageUIState.Initial)

  companion object {
    val Factory: ViewModelProvider.Factory = viewModelFactory {
      initializer {
        TeamPageViewModel(
          loadTeamPageUseCase = LoadTeamPageUseCase.newInstance()
        )
      }
    }
  }
}