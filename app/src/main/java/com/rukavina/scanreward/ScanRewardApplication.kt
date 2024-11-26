package com.rukavina.scanreward

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ScanRewardApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}