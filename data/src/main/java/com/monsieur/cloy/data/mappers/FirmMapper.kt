package com.monsieur.cloy.data.mappers

import com.monsieur.cloy.data.storage.models.BasketItemEntity
import com.monsieur.cloy.data.storage.models.FirmEntity
import com.monsieur.cloy.domain.models.BasketItem
import com.monsieur.cloy.domain.models.Firm

class FirmMapper {
    fun toDomainModel(firmEntity: FirmEntity): Firm {
        return Firm(firmEntity.id, firmEntity.firmName)
    }

    fun toDataModel(firm: Firm): FirmEntity {
        val firmEntity = FirmEntity()
        firmEntity.id = firm.id
        firmEntity.firmName = firm.firmName
        return firmEntity
    }
}