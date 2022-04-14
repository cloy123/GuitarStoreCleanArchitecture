package com.monsieur.cloy.data.mappers

import com.monsieur.cloy.data.storage.models.ProductEntity
import com.monsieur.cloy.data.storage.models.ProductWithFirmAndProductType
import com.monsieur.cloy.data.storage.models.UserEntity
import com.monsieur.cloy.domain.models.Firm
import com.monsieur.cloy.domain.models.Product
import com.monsieur.cloy.domain.models.ProductType
import com.monsieur.cloy.domain.models.User

class ProductMapper {
    fun toDomainModel(product: ProductWithFirmAndProductType): Product {

//        val firm = if (productEntity.firm == null) {
//            null
//        } else {
//            FirmMapper().toDomainModel(productEntity.firm!!)
//        }
//
//        val productType = if (productEntity.type == null) {
//            null
//        } else {
//            ProductTypeMapper().toDomainModel(productEntity.type!!)
//        }

        val firm = Firm(product.firmId, product.firmName)
        val productType = ProductType(product.typeId, product.typeName)

        return Product(
            product.id,
            product.popularity,
            product.quantity,
            product.imagesId,
            firm,
            productType,
            product.model,
            product.specification,
            product.isShow,
            product.price,
            product.discount
        )
    }

    fun toDataModel(product: Product): ProductEntity {
        val productEntity = ProductEntity()
        productEntity.id = product.id
        productEntity.quantity = product.quantity
        productEntity.imagesId = product.imagesId
        productEntity.firmId = product.firmId
        productEntity.typeId = product.typeId
        productEntity.model = product.model
        productEntity.specification = product.specification
        productEntity.isShow = product.isShow
        productEntity.price = product.price
        productEntity.discount = product.discount
        return productEntity
    }
}