package com.monsieur.cloy.data.mappers

import com.monsieur.cloy.data.storage.models.FirmEntity
import com.monsieur.cloy.data.storage.models.ProductTypeEntity
import com.monsieur.cloy.domain.models.Firm
import com.monsieur.cloy.domain.models.ProductType

class ProductTypeMapper {
    fun toDomainModel(productTypeEntity: ProductTypeEntity): ProductType {
        return ProductType(productTypeEntity.id, productTypeEntity.typeName)
    }

    fun toDataModel(productType: ProductType): ProductTypeEntity {
        val productTypeEntity = ProductTypeEntity()
        productTypeEntity.id = productType.id
        productTypeEntity.typeName = productType.typeName
        return productTypeEntity
    }
}