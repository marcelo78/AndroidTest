package com.mac.intacttest.di

import android.content.Context
import com.squareup.picasso.Picasso
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val picassoModule = module {

    single {
        picasso(androidContext())
    }

}

private fun picasso(context: Context) = Picasso.Builder(context)
    .loggingEnabled(true)
    .build()