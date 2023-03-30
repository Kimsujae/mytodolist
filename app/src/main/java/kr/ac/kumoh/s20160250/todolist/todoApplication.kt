package kr.ac.kumoh.s20160250.todolist

import android.app.Application
import kr.ac.kumoh.s20160250.todolist.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level


class todoApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        //Todo Koin Trigger
        startKoin{
            androidLogger(Level.ERROR)
            androidContext(this@todoApplication)
            modules(appModule)
        }
    }
}