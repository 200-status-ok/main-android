package com.example.haminjast.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LoginDataStore(private val context: Context) {
    private val login = stringPreferencesKey("login")
    val getToken: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[login] ?: ""
        }

    suspend fun saveToken(token: String) {
        context.dataStore.edit { preferences ->
            preferences[login] = token
        }
    }

    companion object {
        private const val PREFERENCE_NAME = "LoginDataStore"
        val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCE_NAME)
    }
}