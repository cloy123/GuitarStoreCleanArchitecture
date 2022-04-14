package com.monsieur.cloy.domain.usecase

import com.monsieur.cloy.domain.models.Firm
import com.monsieur.cloy.domain.models.Product
import com.monsieur.cloy.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow

class GetAllProductsUseCase(private val productRepository: ProductRepository) {
    fun execute(): Flow<List<Product>> {
        return productRepository.getAllProducts()
    }
}