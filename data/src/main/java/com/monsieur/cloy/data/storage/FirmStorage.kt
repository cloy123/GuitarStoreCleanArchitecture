package com.monsieur.cloy.data.storage

import com.monsieur.cloy.data.storage.models.FirmEntity
import com.monsieur.cloy.data.db.FirmDao
import kotlinx.coroutines.flow.Flow

class FirmStorage(private val firmDao: FirmDao) {
    fun getAllFirms(): Flow<List<FirmEntity>>{
        return firmDao.getAllFirms()
    }
}