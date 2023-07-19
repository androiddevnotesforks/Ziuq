package com.norrisboat.ziuq.data.repository

import com.russhwolf.settings.Settings
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface SettingsRepository {

    fun isLoggedIn(): Boolean
    fun setIsLoggedIn(status: Boolean)

    fun getUserId(): String
    fun setUserId(id: String)

    fun getDifficulties(): String
    fun setDifficulties(difficulties: String)

}

class SettingsRepositoryImpl : KoinComponent, SettingsRepository {

    private val settings: Settings by inject()

    override fun isLoggedIn(): Boolean {
        return settings.getBoolean(SettingsKey.IsLoggedIn.name, false)
    }

    override fun setIsLoggedIn(status: Boolean) {
        settings.putBoolean(SettingsKey.IsLoggedIn.name, status)
    }

    override fun getUserId(): String {
        return settings.getString(SettingsKey.UserId.name, "")
    }

    override fun setUserId(id: String) {
        settings.putString(SettingsKey.UserId.name, id)
    }

    override fun getDifficulties(): String {
        return settings.getString(SettingsKey.Difficulty.name, "")
    }

    override fun setDifficulties(difficulties: String) {
        settings.putString(SettingsKey.Difficulty.name, difficulties)
    }


}

enum class SettingsKey {
    IsLoggedIn,
    UserId,
    Difficulty,
}