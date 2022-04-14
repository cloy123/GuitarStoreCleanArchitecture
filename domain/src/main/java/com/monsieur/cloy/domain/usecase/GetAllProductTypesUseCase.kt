package com.monsieur.cloy.domain.usecase

import com.monsieur.cloy.domain.models.Firm
import com.monsieur.cloy.domain.models.ProductType
import com.monsieur.cloy.domain.repository.ProductTypeRepository
import kotlinx.coroutines.flow.Flow

class GetAllProductTypesUseCase(private val productTypeRepository: ProductTypeRepository) {
    fun execute(): Flow<List<ProductType>> {
        return productTypeRepository.getAllTypes()
    }
}