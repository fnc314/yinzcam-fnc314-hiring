package com.yinzcam.fnc314.hiring.feature.teampage.domain

import com.yinzcam.fnc314.hiring.core.data.NFLTeamsPage

public sealed class LoadTeamPageResult {
  public data object None : LoadTeamPageResult()
  public data object IsLoading : LoadTeamPageResult()
  public data class Success(
    val teamPage: NFLTeamsPage
  ): LoadTeamPageResult()
  public sealed class Error : LoadTeamPageResult() {
    public data object NoData : Error()
    public data object NoNetwork : Error()
    public data object Unknown : Error()
  }
}
