package com.example.printingapp

import android.app.Application
import com.example.printingapp.data.AppContainer
import com.example.printingapp.data.DefaultAppContainer

class PrinterApplication : Application () {
    lateinit var container : AppContainer
    override fun onCreate(){
        super.onCreate()
        container = DefaultAppContainer()
    }
}