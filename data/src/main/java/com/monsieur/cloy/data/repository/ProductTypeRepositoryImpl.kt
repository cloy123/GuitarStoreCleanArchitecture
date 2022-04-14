package com.monsieur.cloy.data.repository

import com.monsieur.cloy.data.mappers.ProductTypeMapper
import com.monsieur.cloy.data.storage.ProductTypeStorage
import com.monsieur.cloy.domain.models.ProductType
import com.monsieur.cloy.domain.repository.ProductTypeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ProductTypeRepositoryImpl(private val productTypeStorage: ProductTypeStorage): ProductTypeRepository {
    override fun getAllTypes(): Flow<List<ProductType>> {
        val mapper = ProductTypeMapper()
        return productTypeStorage.getAllTypes().map { list ->
            list.map {
                mapper.toDomainModel(it)
            }
        }
    }
}