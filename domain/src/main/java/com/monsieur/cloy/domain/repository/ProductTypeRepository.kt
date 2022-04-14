package com.monsieur.cloy.domain.repository

import com.monsieur.cloy.domain.models.ProductType
import kotlinx.coroutines.flow.Flow


interface ProductTypeRepository {
    fun getAllTypes(): Flow<List<ProductType>>
}