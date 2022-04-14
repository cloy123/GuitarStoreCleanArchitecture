package com.monsieur.cloy.data.storage

import com.monsieur.cloy.data.storage.models.ProductTypeEntity
import com.monsieur.cloy.data.db.ProductTypeDao
import kotlinx.coroutines.flow.Flow

class ProductTypeStorage(private val productTypeDao: ProductTypeDao) {
    fun getAllTypes(): Flow<List<ProductTypeEntity>> {
        return productTypeDao.getAllTypes()
    }
}