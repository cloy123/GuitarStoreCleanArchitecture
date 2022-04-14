package com.monsieur.cloy.domain.usecase

import com.monsieur.cloy.domain.models.BasketItem
import com.monsieur.cloy.domain.repository.BasketItemRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class GetBasketItemsByUserIdUseCase(private val basketItemRepository: BasketItemRepository) {
    fun execute(userId: Int): Flow<List<BasketItem>>{
        return basketItemRepository.getFlowBasketItemsByUserId(userId)
    }
}