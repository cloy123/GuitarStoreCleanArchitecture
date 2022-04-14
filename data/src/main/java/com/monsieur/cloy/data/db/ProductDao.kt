package com.monsieur.cloy.data.db

import androidx.room.*
import com.monsieur.cloy.data.storage.models.ProductEntity
import com.monsieur.cloy.data.storage.models.ProductWithFirmAndProductType
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {



    @Update
    suspend fun updateProduct(product: ProductEntity)
}