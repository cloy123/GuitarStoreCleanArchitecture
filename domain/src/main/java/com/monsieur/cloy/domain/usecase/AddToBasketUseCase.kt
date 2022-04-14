package com.monsieur.cloy.domain.usecase

import com.monsieur.cloy.domain.models.BasketItem
import com.monsieur.cloy.domain.models.Product
import com.monsieur.cloy.domain.repository.BasketItemRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class AddToBasketUseCase(private val basketItemRepository: BasketItemRepository) {
    suspend fun execute(userId: Int, productId: Int) {
        withContext(Dispatchers.IO){
            val basketItem = basketItemRepository.findBasketItem(userId, productId)
            if(basketItem != null){
                basketItem.quantity += 1
                basketItemRepository.updateBasketItem(basketItem)
            }else{
                val newBasketItem = BasketItem(1, userId, productId)
                basketItemRepository.insertBasketItem(newBasketItem)
            }
        }
    }
}