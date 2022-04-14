package com.monsieur.cloy.domain.models

import java.time.LocalDateTime

class History(
    val id: Int,
    val productId: Int,
    val quantity: Int,
    val price: Int,
    val dateTime: LocalDateTime,
    val userId: Int,
    var address: String
) {
}