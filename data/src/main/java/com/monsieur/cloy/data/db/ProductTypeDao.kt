package com.monsieur.cloy.data.db

import androidx.room.*
import com.monsieur.cloy.data.storage.models.ProductTypeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductTypeDao {

    @Query("SELECT * FROM productTypes")
    fun getAllTypes(): Flow<List<ProductTypeEntity>>
}