package com.monsieur.cloy.domain.usecase

import com.monsieur.cloy.domain.models.BasketItem
import com.monsieur.cloy.domain.models.History
import com.monsieur.cloy.domain.repository.BasketItemRepository
import com.monsieur.cloy.domain.repository.HistoryRepository
import kotlinx.coroutines.flow.Flow

class GetHistoryByUserIdUseCase (private val historyRepository: HistoryRepository) {
    fun execute(userId: Int): Flow<List<History>> {
        return historyRepository.getHistoryByUserId(userId)
    }
}