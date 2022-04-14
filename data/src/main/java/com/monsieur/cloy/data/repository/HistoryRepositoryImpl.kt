package com.monsieur.cloy.data.repository

import com.monsieur.cloy.data.mappers.HistoryMapper
import com.monsieur.cloy.data.storage.HistoryStorage
import com.monsieur.cloy.domain.models.History
import com.monsieur.cloy.domain.repository.HistoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class HistoryRepositoryImpl(private val historyStorage: HistoryStorage): HistoryRepository {
    override fun getAllHistory(): Flow<List<History>> {
        val mapper = HistoryMapper()
        return historyStorage.getAllHistory().map { list ->
            list.map {
                mapper.toDomainModel(it)
            }
        }
    }

    override suspend fun insertHistory(history: History) {
        historyStorage.insertHistory(HistoryMapper().toDataModel(history))
    }
}