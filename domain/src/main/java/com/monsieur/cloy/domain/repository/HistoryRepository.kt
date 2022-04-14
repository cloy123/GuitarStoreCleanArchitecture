package com.monsieur.cloy.domain.repository

import com.monsieur.cloy.domain.models.History
import kotlinx.coroutines.flow.Flow

interface HistoryRepository {

    fun getAllHistory(): Flow<List<History>>

    suspend fun insertHistory(history: History)
}