package com.yinzcam.fnc314.hiring.feature.teamschedule.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.yinzcam.fnc314.hiring.core.data.NFLTeamsTriCode
import com.yinzcam.fnc314.hiring.feature.teamschedule.domain.LoadTeamScheduleResult
import com.yinzcam.fnc314.hiring.feature.teamschedule.domain.LoadTeamScheduleUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

public class TeamScheduleViewModel(
  private val loadTeamScheduleUseCase: LoadTeamScheduleUseCase,
) : ViewModel() {

  private val _state: MutableStateFlow<TeamScheduleUIState> =
    MutableStateFlow(TeamScheduleUIState.Initial)

  public val state: StateFlow<TeamScheduleUIState> = _state.asStateFlow()

  init {
    viewModelScope.launch {
      _state.emit(TeamScheduleUIState.IsBusy)
      loadTeamScheduleUseCase(team = NFLTeamsTriCode.GB).collectLatest {
        val newState = when (it) {
          is LoadTeamScheduleResult.Success -> TeamScheduleUIState.Loaded(it)
          else -> TeamScheduleUIState.Error.NoData
        }
        Log.i(
          TeamScheduleViewModel::class.simpleName,
          "SERVICE $it\nCURRENT ${_state.value}\nNEW $newState"
        )
        _state.emit(newState)
      }
    }

  }

  public companion object {
    public val Factory: ViewModelProvider.Factory = viewModelFactory {
      initializer {
        TeamScheduleViewModel(
          loadTeamScheduleUseCase = this[LoadTeamScheduleUseCase]!!
        )
      }
    }
  }
}