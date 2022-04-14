package com.monsieur.cloy.data.repository

import com.monsieur.cloy.data.mappers.FirmMapper
import com.monsieur.cloy.data.storage.FirmStorage
import com.monsieur.cloy.domain.models.Firm
import com.monsieur.cloy.domain.repository.FirmRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FirmRepositoryImpl(private val firmStorage: FirmStorage): FirmRepository {
    override fun getAllFirms(): Flow<List<Firm>> {
        val mapper = FirmMapper()
        return firmStorage.getAllFirms().map { list->
            list.map {
                mapper.toDomainModel(it)
            }
        }
    }
}