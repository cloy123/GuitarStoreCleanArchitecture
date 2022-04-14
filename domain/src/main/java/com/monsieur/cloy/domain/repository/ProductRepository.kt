package com.monsieur.cloy.domain.repository

import com.monsieur.cloy.domain.models.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    fun getAllProducts(): Flow<List<Product>>

    suspend fun updateProduct(product: Product)
}