package com.yinzcam.fnc314.hiring.feature.teamschedule.ui

import com.yinzcam.fnc314.hiring.feature.teamschedule.domain.LoadTeamScheduleResult

sealed interface TeamScheduleUIState {
  data object Initial : TeamScheduleUIState
  data object IsBusy : TeamScheduleUIState
  data class Loaded(val data: LoadTeamScheduleResult.Success) : TeamScheduleUIState
  sealed interface Error : TeamScheduleUIState {
    data object NoData : Error
    data object NoConnection : Error
    data object Unknown : Error
  }
}