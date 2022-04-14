package com.monsieur.cloy.data.storage.models

import androidx.annotation.NonNull
import androidx.room.*
import com.monsieur.cloy.domain.models.Product

@Entity(tableName = "basketItems")
class BasketItemEntity {

    @ColumnInfo(name = "basketItem_id")
    @NonNull
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    @ColumnInfo(name = "basketItemQuantity")
    @NonNull
    var quantity: Int = 0

    @ColumnInfo(name = "userId")
    @NonNull
    var userId: Int = 0

    @ColumnInfo(name = "productId")
    @NonNull
    var productId: Int = 0
}