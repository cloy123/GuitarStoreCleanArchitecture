package com.monsieur.cloy.domain.usecase


import com.monsieur.cloy.domain.repository.BasketItemRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ChangeQuantityBasketItemUseCase(
    private val basketItemRepository: BasketItemRepository,
) {
    suspend fun execute(userId: Int, productId: Int, quantity: Int) {
        withContext(Dispatchers.IO) {
            val basketItem = basketItemRepository.findBasketItem(userId, productId)
            if (basketItem != null) {
                val newQuantity = basketItem.quantity + quantity
                if (newQuantity <= 0) {
                    basketItemRepository.deleteBasketItemById(basketItem.id)
                }else{
                    basketItem.quantity = newQuantity
                    basketItemRepository.updateBasketItem(basketItem)
                }
            }
        }
    }
}