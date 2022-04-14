package com.monsieur.cloy.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.monsieur.cloy.data.storage.models.HistoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoryDao {

    @Insert
    suspend fun insertHistory(historyEntity: HistoryEntity)

    @Query("SELECT * FROM history")
    fun getAllHistory(): Flow<List<HistoryEntity>>
}