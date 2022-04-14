package com.monsieur.cloy.domain.repository

import com.monsieur.cloy.domain.models.Firm
import kotlinx.coroutines.flow.Flow

interface FirmRepository {
    fun getAllFirms(): Flow<List<Firm>>
}