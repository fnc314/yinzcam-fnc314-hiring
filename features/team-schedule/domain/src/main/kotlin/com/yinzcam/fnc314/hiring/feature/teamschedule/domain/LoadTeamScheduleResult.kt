package com.yinzcam.fnc314.hiring.feature.teamschedule.domain

import com.yinzcam.fnc314.hiring.core.data.NFLTeamsPage

public sealed class LoadTeamScheduleResult {
  public data object None : LoadTeamScheduleResult()
  public data object IsLoading : LoadTeamScheduleResult()
  public data class Success(
    val teamPage: NFLTeamsPage
  ): LoadTeamScheduleResult()
  public sealed class Error : LoadTeamScheduleResult() {
    public data object NoData : Error()
    public data object NoNetwork : Error()
    public data object Unknown : Error()
  }
}
