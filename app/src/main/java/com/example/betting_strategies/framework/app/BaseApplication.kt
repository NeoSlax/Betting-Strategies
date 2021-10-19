package com.example.betting_strategies.framework.app

import android.app.Application
import com.example.betting_strategies.framework.di.ApplicationComponent
import com.example.betting_strategies.framework.di.DaggerApplicationComponent

class BaseApplication : Application() {

    private var appComponent: ApplicationComponent = DaggerApplicationComponent
        .factory()
        .create(
            application = this,
            applicationContext = this
        )



    fun getAppComponent(): ApplicationComponent {
        return appComponent
    }

}