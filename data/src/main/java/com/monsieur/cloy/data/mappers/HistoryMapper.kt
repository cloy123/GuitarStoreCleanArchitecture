package com.monsieur.cloy.data.mappers

import com.monsieur.cloy.data.storage.models.FirmEntity
import com.monsieur.cloy.data.storage.models.HistoryEntity
import com.monsieur.cloy.domain.models.Firm
import com.monsieur.cloy.domain.models.History

class HistoryMapper {
    fun toDomainModel(historyEntity: HistoryEntity): History {
        return History(
            historyEntity.id,
            historyEntity.productId,
            historyEntity.quantity,
            historyEntity.price,
            historyEntity.dateTime,
            historyEntity.userId,
            historyEntity.address
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