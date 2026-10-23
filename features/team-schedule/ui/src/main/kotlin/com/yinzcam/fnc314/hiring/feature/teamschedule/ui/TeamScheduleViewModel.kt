package com.yinzcam.fnc314.hiring.feature.teamschedule.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.yinzcam.fnc314.hiring.core.data.NFLTeams
import com.yinzcam.fnc314.hiring.feature.teamschedule.domain.LoadTeamScheduleResult
import com.yinzcam.fnc314.hiring.feature.teamschedule.domain.LoadTeamScheduleUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class TeamScheduleViewModel(
  private val loadTeamScheduleUseCase: LoadTeamScheduleUseCase,
) : ViewModel() {

  private val _state: MutableStateFlow<TeamScheduleUIState> =
    MutableStateFlow(TeamScheduleUIState.Initial)

  val state: StateFlow<TeamScheduleUIState> = _state

  init {
    viewModelScope.launch {
      _state.emit(TeamScheduleUIState.IsBusy)
      loadTeamScheduleUseCase(team = NFLTeams.GB).collectLatest {
        val newState = when (it) {
          is LoadTeamScheduleResult.Success -> TeamScheduleUIState.Loaded(it)
          else -> TeamScheduleUIState.Error.NoData
        }
        _state.emit(newState)
      }
    }

  }

  companion object {
    val Factory: ViewModelProvider.Factory = viewModelFactory {
      initializer {
        TeamScheduleViewModel(
          loadTeamScheduleUseCase = LoadTeamScheduleUseCase.newInstance()
        )
      }
    }
  }
}