package com.monsieur.cloy.data.db

import androidx.room.*
import com.monsieur.cloy.data.storage.models.FirmEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FirmDao {
    @Query("SELECT * FROM firms")
    fun getAllFirms(): Flow<List<FirmEntity>>
}