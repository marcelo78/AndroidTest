package com.mac.intacttest

import android.app.Application
import com.mac.intacttest.activities.di.productsModule
import com.mac.intacttest.di.picassoModule
import com.mac.intacttest.di.retrofitModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@MyApp)
            modules(listOf(retrofitModule, picassoModule, productsModule))
        }
    }

}