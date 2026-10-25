package com.yinzcam.fnc314.hiring.core.navigation

import kotlinx.serialization.Serializable

@Serializable
public sealed interface NavDestinations {

    @Serializable
    public data object Home : NavDestinations

    @Serializable
    public data object TeamSchedule: NavDestinations

}