package com.mac.intacttest.activities.di

import com.mac.intacttest.activities.viewmodel.ProductsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val productsModule = module {

    viewModel {
        ProductsViewModel(get())
    }

}