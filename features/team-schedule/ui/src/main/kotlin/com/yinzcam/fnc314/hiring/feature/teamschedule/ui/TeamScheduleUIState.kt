package com.yinzcam.fnc314.hiring.feature.teamschedule.ui

import com.yinzcam.fnc314.hiring.feature.teamschedule.domain.LoadTeamScheduleResult

public sealed interface TeamScheduleUIState {
  public data object Initial : TeamScheduleUIState
  public data object IsBusy : TeamScheduleUIState
  public data class Loaded(val data: LoadTeamScheduleResult.Success) : TeamScheduleUIState
  public sealed interface Error : TeamScheduleUIState {
    public data object NoData : Error
    public data object NoConnection : Error
    public data object Unknown : Error
  }
}