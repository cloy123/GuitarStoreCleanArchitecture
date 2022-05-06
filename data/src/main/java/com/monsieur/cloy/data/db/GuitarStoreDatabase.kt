package com.monsieur.cloy.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.monsieur.cloy.data.storage.models.*


@Database(entities = [BasketItemEntity::class, FirmEntity::class, HistoryEntity::class, ProductEntity::class, ProductTypeEntity::class, UserEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class GuitarStoreDatabase: RoomDatabase() {
    abstract fun basketItemDao(): BasketItemDao
    abstract fun firmDao(): FirmDao
    abstract fun historyDao(): HistoryDao
    abstract fun productDao(): ProductDao
    abstract fun productTypeDao(): ProductTypeDao
    abstract fun userDao(): UserDao
    abstract fun productWithFirmAndProductTypeDao(): ProductWithFirmAndProductTypeDao
    abstract fun basketItemWithProductDao(): BasketItemWithProductDao
    abstract fun historyWithProductDao(): HistoryWithProductDao
}