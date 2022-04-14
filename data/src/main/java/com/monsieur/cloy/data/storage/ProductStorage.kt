package com.monsieur.cloy.data.storage

import com.monsieur.cloy.data.db.ProductDao
import com.monsieur.cloy.data.db.ProductWithFirmAndProductTypeDao
import com.monsieur.cloy.data.storage.models.ProductEntity
import com.monsieur.cloy.data.storage.models.ProductWithFirmAndProductType
import com.monsieur.cloy.domain.models.Product
import kotlinx.coroutines.flow.Flow

class ProductStorage(private val productDao: ProductDao, private val productWithFirmAndProductTypeDao: ProductWithFirmAndProductTypeDao) {
    fun getAllProducts(): Flow<List<ProductWithFirmAndProductType>> {
        return productWithFirmAndProductTypeDao.getAllProducts()
    }

    suspend fun updateProduct(product: ProductEntity) {
        productDao.updateProduct(product)
    }
}