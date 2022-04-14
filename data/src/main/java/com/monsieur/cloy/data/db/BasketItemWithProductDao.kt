package com.monsieur.cloy.data.db

import androidx.room.Dao
import androidx.room.Query
import com.monsieur.cloy.data.storage.models.BasketItemWithProduct
import kotlinx.coroutines.flow.Flow

@Dao
interface BasketItemWithProductDao {
    @Query("SELECT * FROM basketItems  INNER JOIN (SELECT * FROM products INNER JOIN firms on products.firmId = firms.firm_id INNER JOIN productTypes on products.typeId = productTypes.productType_id) as products on products.product_id = basketItems.productId  WHERE userId =:userId AND productId =:productId")
    suspend fun findBasketItem(userId: Int, productId: Int): BasketItemWithProduct?

    @Query("SELECT * FROM basketItems INNER JOIN (SELECT * FROM products INNER JOIN firms on products.firmId = firms.firm_id INNER JOIN productTypes on products.typeId = productTypes.productType_id) as products on products.product_id = basketItems.productId WHERE userId = :userId")
    fun findFlowBasketItemsByUserId(userId: Int): Flow<List<BasketItemWithProduct>>

    @Query("SELECT * FROM basketItems  INNER JOIN (SELECT * FROM products INNER JOIN firms on products.firmId = firms.firm_id INNER JOIN productTypes on products.typeId = productTypes.productType_id) as products on products.product_id = basketItems.productId  WHERE userId = :userId")
    suspend fun findListBasketItemsByUserId(userId: Int): List<BasketItemWithProduct>
}