package com.yinzcam.fnc314.hiring.core.navigation

import com.yinzcam.fnc314.hiring.core.data.NFLTeams
import kotlinx.serialization.Serializable

@Serializable
sealed interface NavDestinations {

    @Serializable data object Home : NavDestinations

    @Serializable data class TeamPage(
        val team: NFLTeams
    ): NavDestinations

}