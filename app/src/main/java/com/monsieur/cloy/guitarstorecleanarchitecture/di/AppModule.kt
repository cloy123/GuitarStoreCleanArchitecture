package com.monsieur.cloy.guitarstorecleanarchitecture.di

import com.monsieur.cloy.guitarstorecleanarchitecture.presentation.viewModels.MainViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel<MainViewModel> {
        MainViewModel(
            androidApplication(),
            addToBasketUseCase = get(),
            changeQuantityBasketItemUseCase = get(),
            confirmOrderUseCase = get(),
            deleteBasketItemByIdUseCase = get(),
            getAllFirmsUseCase = get(),
            getAllProductsUseCase = get(),
            getAllProductTypesUseCase = get(),
            getBasketItemsByUserIdUseCase = get(),
            loginUseCase = get(),
            registerUserUseCase = get()
        )
    }
}