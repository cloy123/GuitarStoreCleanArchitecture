package com.monsieur.cloy.domain.usecase


import com.monsieur.cloy.domain.repository.BasketItemRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DeleteBasketItemByIdUseCase(private val basketItemRepository: BasketItemRepository) {
    suspend fun execute(basketItemId: Int){
        withContext(Dispatchers.IO) {
            basketItemRepository.deleteBasketItemById(basketItemId)
        }
    }
}