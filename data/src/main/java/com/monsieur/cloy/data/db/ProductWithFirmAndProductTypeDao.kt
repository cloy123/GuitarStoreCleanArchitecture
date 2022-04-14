package com.monsieur.cloy.data.db

import androidx.room.Dao
import androidx.room.Query
import com.monsieur.cloy.data.storage.models.ProductWithFirmAndProductType
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductWithFirmAndProductTypeDao {

    @Query("SELECT *, sum(history.historyQuantity) as popularity FROM products LEFT JOIN (SELECT quantity as historyQuantity, productId FROM history) as history ON history.productId = products.product_id INNER JOIN firms on products.firmId = firms.firm_id INNER JOIN productTypes on products.typeId = productTypes.productType_id  GROUP BY products.product_id, products.quantity")
    //@Query("SELECT products.product_id, products.quantity, products.imagesId, products.firmId, products.typeId, products.model, products.specification, products.isShow, products.price, products.discount, firms.firmName, productTypes.typeName, sum(history.quantity) as popularity FROM products LEFT JOIN (SELECT quantity, productId FROM history) as history ON history.productId = products.product_id INNER JOIN firms on products.firmId = firms.firm_id INNER JOIN productTypes on products.typeId = productTypes.productType_id  GROUP BY products.product_id, products.quantity")
    //@Query("SELECT * FROM products INNER JOIN (SELECT SUM(quantity) as popularity, productId FROM history) as history ON history.productId = products.product_id INNER JOIN firms on products.firmId = firms.firm_id INNER JOIN productTypes on products.typeId = productTypes.productType_id")
    fun getAllProducts(): Flow<List<ProductWithFirmAndProductType>>
}