package com.monsieur.cloy.data.mappers

import com.monsieur.cloy.data.storage.models.BasketItemWithProduct
import com.monsieur.cloy.data.storage.models.FirmEntity
import com.monsieur.cloy.data.storage.models.HistoryEntity
import com.monsieur.cloy.data.storage.models.HistoryWithProduct
import com.monsieur.cloy.domain.models.*

class HistoryMapper {

    fun toDomainModel(historyWithProduct: HistoryWithProduct): History {
        val firm = Firm(historyWithProduct.firmId, historyWithProduct.firmName)
        val type = ProductType(historyWithProduct.typeId, historyWithProduct.typeName)

        val product = Product(
            historyWithProduct.productId,
            historyWithProduct.popularity,
            historyWithProduct.productQuantity,
            historyWithProduct.imagesId,
            firm,
            type,
            historyWithProduct.model,
            historyWithProduct.specification,
            historyWithProduct.isShow,
            historyWithProduct.productPrice,
            historyWithProduct.discount
        )

        return History(
            historyWithProduct.id,
            historyWithProduct.productId,
            historyWithProduct.quantity,
            historyWithProduct.price,
            historyWithProduct.dateTime,
            historyWithProduct.userId,
            historyWithProduct.address,
            product
        )
    }

    fun toDataModel(history: History): HistoryEntity {
        val historyEntity = HistoryEntity()
        historyEntity.id = history.id
        historyEntity.productId = history.productId
        historyEntity.quantity = history.quantity
        historyEntity.price = history.price
        historyEntity.dateTime = history.dateTime
        historyEntity.userId = history.userId
        historyEntity.address = history.address
        return historyEntity
    }
}