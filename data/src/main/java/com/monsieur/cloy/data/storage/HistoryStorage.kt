package com.monsieur.cloy.data.storage

import com.monsieur.cloy.data.db.HistoryDao
import com.monsieur.cloy.data.db.HistoryWithProductDao
import com.monsieur.cloy.data.storage.models.HistoryEntity
import com.monsieur.cloy.data.storage.models.HistoryWithProduct
import com.monsieur.cloy.domain.models.History
import kotlinx.coroutines.flow.Flow

class HistoryStorage(private val historyDao: HistoryDao, private val historyWithProductDao: HistoryWithProductDao) {
    fun getHistoryByUserId(userId: Int): Flow<List<HistoryWithProduct>>{
        return historyWithProductDao.getHistoryByUserId(userId)
    }

    suspend fun insertHistory(history: HistoryEntity) {
        historyDao.insertHistory(history)
    }
}