package com.saikiran.practiseapp.util

import android.content.Context
import com.saikiran.practiseapp.MyApplication

class PrefUtil {
    companion object {
        private const val SP_NAME = "DEMO_APP"
        const val SP_LAST_SYNCED_TIME = "LAST_SYNC_TIME"

        fun setLong(key: String, defaultValue: Long) {
            val preferences =
                MyApplication.shared().getSharedPreferences(SP_NAME, Context.MODE_PRIVATE)
            val editor = preferences.edit()
            editor.putLong(key, defaultValue)
            editor.apply()
        }

        fun getLong(key: String): Long {
            val preferences =
                MyApplication.shared().getSharedPreferences(SP_NAME, Context.MODE_PRIVATE)
            return preferences.getLong(key, System.currentTimeMillis() + 7260000L)
        }
    }
}