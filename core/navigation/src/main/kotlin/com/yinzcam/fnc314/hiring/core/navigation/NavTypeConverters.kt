package com.yinzcam.fnc314.hiring.core.navigation

import android.os.Bundle
import androidx.navigation.NavType
import com.yinzcam.fnc314.hiring.core.data.NFLTeamsTriCode
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString
import kotlinx.serialization.decodeFromString
import kotlin.reflect.typeOf

public val NFLTeamsTriCodeType: NavType<NFLTeamsTriCode> = object : NavType<NFLTeamsTriCode>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): NFLTeamsTriCode? {
        return bundle.getString(key)?.let { Json.decodeFromString<NFLTeamsTriCode>(it) }
    }

    override fun parseValue(value: String): NFLTeamsTriCode {
        return Json.decodeFromString<NFLTeamsTriCode>(value)
    }

    override fun serializeAsValue(value: NFLTeamsTriCode): String {
        return Json.encodeToString(value)
    }

    override fun put(bundle: Bundle, key: String, value: NFLTeamsTriCode) {
        bundle.putString(key, Json.encodeToString(value))
    }
}
