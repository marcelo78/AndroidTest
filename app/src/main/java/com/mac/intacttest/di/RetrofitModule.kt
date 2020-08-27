package com.mac.intacttest.di

import com.mac.intacttest.backend.ServiceUtil
import com.mac.intacttest.tools.Constants
import kotlinx.serialization.UnstableDefault
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val retrofitModule = module {

    single {
        retrofit(Constants.BASE_URL)
    }

    single {
        get<Retrofit>().create(ServiceUtil::class.java)
    }

}

@OptIn(UnstableDefault::class)
private fun retrofit(baseUrl: String) = Retrofit.Builder()
    .baseUrl(baseUrl)
    .addConverterFactory(GsonConverterFactory.create())
    .build()
