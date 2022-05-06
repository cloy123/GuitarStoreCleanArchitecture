package com.monsieur.cloy.guitarstorecleanarchitecture.di

import androidx.room.Room
import com.monsieur.cloy.data.db.*
import com.monsieur.cloy.data.repository.*
import com.monsieur.cloy.data.storage.*
import com.monsieur.cloy.domain.repository.*
import com.monsieur.cloy.domain.usecase.GetHistoryByUserIdUseCase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {
    single<BasketItemStorage> {
        BasketItemStorage(basketItemDao = get(), basketItemWithProductDao = get())
    }
    single<FirmStorage> {
        FirmStorage(firmDao = get())
    }
    single<HistoryStorage> {
        HistoryStorage(historyDao = get(), historyWithProductDao = get())
    }
    single<ProductStorage> {
        ProductStorage(productDao = get(), productWithFirmAndProductTypeDao = get())
    }
    single<ProductTypeStorage> {
        ProductTypeStorage(productTypeDao = get())
    }
    single<UserStorage> {
        UserStorage(userDao = get())
    }
    single<GuitarStoreDatabase> {
        Room.databaseBuilder(
            androidContext(),
            GuitarStoreDatabase::class.java,
            "guitar_store_database"
        ).createFromAsset("database/guitar_store_database.db")
            .build()
    }
    single<HistoryWithProductDao> {
        get<GuitarStoreDatabase>().historyWithProductDao()
    }
    single<BasketItemDao> {
        get<GuitarStoreDatabase>().basketItemDao()
    }

    single<FirmDao> {
        get<GuitarStoreDatabase>().firmDao()
    }
    single<HistoryDao> {
        get<GuitarStoreDatabase>().historyDao()
    }
    single<ProductDao> {
        get<GuitarStoreDatabase>().productDao()
    }
    single<ProductTypeDao> {
        get<GuitarStoreDatabase>().productTypeDao()
    }
    single<UserDao> {
        get<GuitarStoreDatabase>().userDao()
    }
    single<ProductWithFirmAndProductTypeDao> {
        get<GuitarStoreDatabase>().productWithFirmAndProductTypeDao()
    }
    single<BasketItemWithProductDao> {
        get<GuitarStoreDatabase>().basketItemWithProductDao()
    }
    single<BasketItemRepository> {
        BasketItemRepositoryImpl(basketItemStorage = get())
    }
    single<FirmRepository> {
        FirmRepositoryImpl(firmStorage = get())
    }
    single<HistoryRepository> {
        HistoryRepositoryImpl(historyStorage = get())
    }
    single<ProductRepository> {
        ProductRepositoryImpl(productStorage = get())
    }
    single<ProductTypeRepository> {
        ProductTypeRepositoryImpl(productTypeStorage = get())
    }
    single<UserRepository> {
        UserRepositoryImpl(userStorage = get())
    }
}