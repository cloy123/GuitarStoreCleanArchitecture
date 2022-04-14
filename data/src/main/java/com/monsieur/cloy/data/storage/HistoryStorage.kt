package com.monsieur.cloy.data.storage

import com.monsieur.cloy.data.db.HistoryDao
import com.monsieur.cloy.data.storage.models.HistoryEntity
import com.monsieur.cloy.domain.models.History
import kotlinx.coroutines.flow.Flow

class HistoryStorage(private val historyDao: HistoryDao) {
    fun getAllHistory(): Flow<List<HistoryEntity>> {
        return historyDao.getAllHistory()
    }

    suspend fun insertHistory(history: HistoryEntity) {
        historyDao.insertHistory(history)
    }
}