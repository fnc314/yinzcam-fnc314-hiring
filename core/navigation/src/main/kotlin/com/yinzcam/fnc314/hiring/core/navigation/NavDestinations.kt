package com.yinzcam.fnc314.hiring.core.navigation

import com.yinzcam.fnc314.hiring.core.data.NFLTeamsTriCode
import kotlinx.serialization.Serializable

@Serializable
public sealed interface NavDestinations {

    @Serializable
    public data object Home : NavDestinations

    @Serializable
    public data class TeamSchedule(
        val team: NFLTeamsTriCode
    ): NavDestinations

}