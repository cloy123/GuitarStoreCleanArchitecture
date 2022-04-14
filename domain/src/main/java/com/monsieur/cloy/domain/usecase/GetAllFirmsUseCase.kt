package com.monsieur.cloy.domain.usecase

import com.monsieur.cloy.domain.models.Firm
import com.monsieur.cloy.domain.repository.FirmRepository
import kotlinx.coroutines.flow.Flow

class GetAllFirmsUseCase(private val firmRepository: FirmRepository) {
    fun execute(): Flow<List<Firm>> {
        return firmRepository.getAllFirms()
    }
}