package com.sunnyweather.android

import android.app.Application
import android.content.Context
import android.media.session.MediaSession

class SunnyWeatherApplication : Application() {
    companion object {
        lateinit var context: Context
        const val Token = "nr1bAH7sq2Irsene"
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

    fun main() {

    }
}
