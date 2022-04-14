package com.monsieur.cloy.data.db

import androidx.room.*
import com.monsieur.cloy.data.storage.models.BasketItemEntity
import com.monsieur.cloy.data.storage.models.BasketItemWithProduct
import kotlinx.coroutines.flow.Flow

@Dao
interface BasketItemDao {

    @Update
    suspend fun updateBasketItem(basketItem: BasketItemEntity)

    @Insert
    suspend fun insertBasketItem(basketItem: BasketItemEntity)

    @Query("DELETE FROM basketItems WHERE basketItem_id = :id")
    suspend fun deleteBasketItemById(id: Int)
}