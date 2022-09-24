package com.saikiran.practiseapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {
    companion object {
        lateinit var instance: MyApplication

        fun shared(): MyApplication {
            return instance
        }
    }

    init {
        this.also { instance = it }
    }
}