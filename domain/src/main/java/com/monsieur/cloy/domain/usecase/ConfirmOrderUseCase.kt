package com.monsieur.cloy.domain.usecase

import com.monsieur.cloy.domain.common.ConfirmOrderResult
import com.monsieur.cloy.domain.models.History
import com.monsieur.cloy.domain.models.Product
import com.monsieur.cloy.domain.models.User
import com.monsieur.cloy.domain.repository.BasketItemRepository
import com.monsieur.cloy.domain.repository.HistoryRepository
import com.monsieur.cloy.domain.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import java.time.LocalDateTime

class ConfirmOrderUseCase(
    private val historyRepository: HistoryRepository,
    private val basketItemRepository: BasketItemRepository,
    private val productRepository: ProductRepository
) {
    suspend fun execute(userId: Int, address: String): Flow<ConfirmOrderResult> {
        return flow {
            val basketItems = basketItemRepository.getListBasketItemsByUserId(userId)
            for (item in basketItems) {
                if (item.product == null || item.product.quantity < item.quantity) {
                    emit(ConfirmOrderResult(false))
                    return@flow
                }
            }

            for (item in basketItems) {
                historyRepository.insertHistory(
                    History(
                        0,
                        item.productId,
                        item.quantity,
                        Product.calculatePrice(item.product!!.price, item.product!!.discount),
                        LocalDateTime.now(),
                        item.userId,
                        address,
                        null
                    )
                )
                val newProduct = item.product
                newProduct.quantity -= item.quantity
                productRepository.updateProduct(newProduct)
                basketItemRepository.deleteBasketItemById(item.id)
            }
            emit(ConfirmOrderResult(true))
        }
    }
}