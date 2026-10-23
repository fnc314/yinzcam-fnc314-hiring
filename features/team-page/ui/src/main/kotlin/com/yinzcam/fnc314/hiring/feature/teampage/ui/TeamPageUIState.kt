package com.yinzcam.fnc314.hiring.feature.teampage.ui

sealed interface TeamPageUIState {
  data object Initial : TeamPageUIState
  data object IsBusy : TeamPageUIState
  data class Loaded(val data: Any) : TeamPageUIState
  sealed interface Error : TeamPageUIState {
    data object NoData : Error
    data object NoConnection : Error
    data object Unknown : Error
  }
}