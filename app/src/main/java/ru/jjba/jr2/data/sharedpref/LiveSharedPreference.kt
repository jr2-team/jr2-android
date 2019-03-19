package ru.jjba.jr2.data.sharedpref

import android.content.SharedPreferences
import androidx.lifecycle.LiveData

@Suppress("UNCHECKED_CAST")
class LiveSharedPreference<T>(
        private val sharedPreferences: SharedPreferences,
        private val preferenceKey: String,
        private val defaultValue: T
) : LiveData<T>() {
    private val sharedPreferenceListener = SharedPreferences.OnSharedPreferenceChangeListener { sharedPreferences, key ->
        if (key == preferenceKey) {
            value = getValueFromPreferences() as T
        }
    }

    override fun onActive() {
        super.onActive()
        value = getValueFromPreferences() as T
        sharedPreferences.registerOnSharedPreferenceChangeListener(sharedPreferenceListener)
    }

    override fun onInactive() {
        super.onInactive()
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(sharedPreferenceListener)
    }

    private fun getValueFromPreferences(): Any = when (defaultValue) {
        is Float -> sharedPreferences.getFloat(preferenceKey, defaultValue)
        is Boolean -> sharedPreferences.getBoolean(preferenceKey, defaultValue)
        is Int -> sharedPreferences.getInt(preferenceKey, defaultValue)
        is Long -> sharedPreferences.getLong(preferenceKey, defaultValue)
        is String -> sharedPreferences.getString(preferenceKey, defaultValue)
        else -> throw IllegalArgumentException("This type can't be load from preference")
    }
}