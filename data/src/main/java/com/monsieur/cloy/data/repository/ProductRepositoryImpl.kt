package com.monsieur.cloy.data.repository

import com.monsieur.cloy.data.mappers.ProductMapper
import com.monsieur.cloy.data.storage.ProductStorage
import com.monsieur.cloy.domain.models.Product
import com.monsieur.cloy.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ProductRepositoryImpl(private val productStorage: ProductStorage): ProductRepository {
    override fun getAllProducts(): Flow<List<Product>> {
        val mapper = ProductMapper()
        return productStorage.getAllProducts().map { list->
            list.map {
                mapper.toDomainModel(it)
            }
        }
    }

    override suspend fun updateProduct(product: Product) {
        productStorage.updateProduct(ProductMapper().toDataModel(product))
    }
}