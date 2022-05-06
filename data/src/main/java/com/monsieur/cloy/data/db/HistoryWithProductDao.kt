package com.monsieur.cloy.data.db

import androidx.room.Dao
import androidx.room.Query
import com.monsieur.cloy.data.storage.models.BasketItemWithProduct
import com.monsieur.cloy.data.storage.models.HistoryEntity
import com.monsieur.cloy.data.storage.models.HistoryWithProduct
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoryWithProductDao {

    @Query("SELECT products.quantity as productQuantity, history.quantity as historyQuantity, products.price as productPrice, * FROM history INNER JOIN (SELECT * FROM products INNER JOIN firms on products.firmId = firms.firm_id INNER JOIN productTypes on products.typeId = productTypes.productType_id) as products on products.product_id = history.productId WHERE userId = :userId")
    fun getHistoryByUserId(userId: Int): Flow<List<HistoryWithProduct>>
}