package com.example.haminjast

import android.app.Application
import android.content.Context

class HaminjastApplication : Application() {
    companion object{
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

}