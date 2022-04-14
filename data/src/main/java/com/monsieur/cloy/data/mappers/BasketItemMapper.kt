package com.monsieur.cloy.data.mappers

import com.monsieur.cloy.data.storage.models.BasketItemEntity
import com.monsieur.cloy.data.storage.models.BasketItemWithProduct
import com.monsieur.cloy.domain.models.BasketItem
import com.monsieur.cloy.domain.models.Firm
import com.monsieur.cloy.domain.models.Product
import com.monsieur.cloy.domain.models.ProductType

class BasketItemMapper {
    fun toDomainModel(basketItem: BasketItemWithProduct): BasketItem {

//        val product = if (basketItemEntity.product == null) {
//            null
//        } else {
//            ProductMapper().toDomainModel(basketItemEntity.product!!)
//        }
        val firm = Firm(basketItem.firmId, basketItem.firmName)
        val type = ProductType(basketItem.typeId, basketItem.typeName)

        val product = Product(
            basketItem.productId,
            basketItem.popularity,
            basketItem.productQuantity,
            basketItem.imagesId,
            firm,
            type,
            basketItem.model,
            basketItem.specification,
            basketItem.isShow,
            basketItem.price,
            basketItem.discount
        )

        return BasketItem(
            basketItem.id,
            basketItem.basketItemQuantity,
            basketItem.userId,
            product
        )
    }

    fun toDataModel(basketItem: BasketItem): BasketItemEntity {
        val basketItemEntity = BasketItemEntity()
        basketItemEntity.id = basketItem.id
        basketItemEntity.productId = basketItem.productId
        basketItemEntity.quantity = basketItem.quantity
        basketItemEntity.userId = basketItem.userId
        return basketItemEntity
    }
}