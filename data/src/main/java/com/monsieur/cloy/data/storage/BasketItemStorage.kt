package com.monsieur.cloy.data.storage

import com.monsieur.cloy.data.db.BasketItemDao
import com.monsieur.cloy.data.db.BasketItemWithProductDao
import com.monsieur.cloy.data.storage.models.BasketItemEntity
import com.monsieur.cloy.data.storage.models.BasketItemWithProduct
import com.monsieur.cloy.domain.models.BasketItem
import kotlinx.coroutines.flow.Flow

class BasketItemStorage(private val basketItemDao: BasketItemDao, private val basketItemWithProductDao: BasketItemWithProductDao) {

    fun findFlowBasketItemsByUserId(userId: Int): Flow<List<BasketItemWithProduct>> {
        return basketItemWithProductDao.findFlowBasketItemsByUserId(userId)
    }

    suspend fun findListBasketItemsByUserId(userId: Int): List<BasketItemWithProduct> {
        return basketItemWithProductDao.findListBasketItemsByUserId(userId)
    }

    suspend fun findBasketItem(userId: Int, productId: Int): BasketItemWithProduct?{
        return basketItemWithProductDao.findBasketItem(userId, productId)
    }

    suspend fun insertBasketItem(basketItem: BasketItemEntity) {
        basketItemDao.insertBasketItem(basketItem)
    }

    suspend fun deleteBasketItemById(id: Int) {
        basketItemDao.deleteBasketItemById(id)
    }

    suspend fun updateBasketItem(basketItem: BasketItemEntity) {
        basketItemDao.updateBasketItem(basketItem)
    }
}