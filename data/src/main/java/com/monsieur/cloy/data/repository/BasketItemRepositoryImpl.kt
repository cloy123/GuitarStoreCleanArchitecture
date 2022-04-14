package com.monsieur.cloy.data.repository

import com.monsieur.cloy.data.mappers.BasketItemMapper
import com.monsieur.cloy.data.storage.BasketItemStorage
import com.monsieur.cloy.domain.models.BasketItem
import com.monsieur.cloy.domain.repository.BasketItemRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class BasketItemRepositoryImpl(private val basketItemStorage: BasketItemStorage): BasketItemRepository {

    override suspend fun getListBasketItemsByUserId(userId: Int): List<BasketItem> {
        val mapper = BasketItemMapper()
        return basketItemStorage.findListBasketItemsByUserId(userId).map {
            mapper.toDomainModel(it)
        }
    }

    override fun getFlowBasketItemsByUserId(userId: Int): Flow<List<BasketItem>> {
        val mapper = BasketItemMapper()
        return basketItemStorage.findFlowBasketItemsByUserId(userId).map { list ->
            list.map {
                mapper.toDomainModel(it)
            }
        }
    }

    override suspend fun findBasketItem(userId: Int, productId: Int): BasketItem? {
        val basketItemEntity = basketItemStorage.findBasketItem(userId, productId)
        return if(basketItemEntity == null){
            null
        }else{
            BasketItemMapper().toDomainModel(basketItemEntity)
        }
    }

    override suspend fun insertBasketItem(basketItem: BasketItem) {
        basketItemStorage.insertBasketItem(BasketItemMapper().toDataModel(basketItem))
    }

    override suspend fun deleteBasketItemById(id: Int) {
        basketItemStorage.deleteBasketItemById(id)
    }


    override suspend fun updateBasketItem(basketItem: BasketItem) {
        basketItemStorage.updateBasketItem(BasketItemMapper().toDataModel(basketItem))
    }
}