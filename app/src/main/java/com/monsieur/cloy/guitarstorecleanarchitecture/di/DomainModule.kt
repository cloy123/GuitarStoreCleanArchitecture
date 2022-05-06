package com.monsieur.cloy.guitarstorecleanarchitecture.di

import com.monsieur.cloy.data.db.GuitarStoreDatabase
import com.monsieur.cloy.domain.usecase.*
import org.koin.dsl.module

val domainModule = module {
    factory<AddToBasketUseCase> {
        AddToBasketUseCase(basketItemRepository = get())
    }
    factory<ChangeQuantityBasketItemUseCase> {
        ChangeQuantityBasketItemUseCase(basketItemRepository = get())
    }
    factory<ConfirmOrderUseCase> {
        ConfirmOrderUseCase(historyRepository = get(), basketItemRepository = get(), productRepository = get())
    }
    factory<DeleteBasketItemByIdUseCase> {
        DeleteBasketItemByIdUseCase(basketItemRepository = get())
    }
    factory<GetAllFirmsUseCase> {
        GetAllFirmsUseCase(firmRepository = get())
    }
    factory<GetAllProductsUseCase> {
        GetAllProductsUseCase(productRepository = get())
    }
    factory<GetAllProductTypesUseCase> {
        GetAllProductTypesUseCase(productTypeRepository = get())
    }
    factory<LoginUseCase> {
        LoginUseCase(userRepository = get())
    }
    factory<RegisterUserUseCase> {
        RegisterUserUseCase(userRepository = get())
    }
    factory<GetBasketItemsByUserIdUseCase> {
        GetBasketItemsByUserIdUseCase(basketItemRepository = get())
    }
    factory<GetHistoryByUserIdUseCase> {
        GetHistoryByUserIdUseCase(historyRepository = get())
    }
}