package com.monsieur.cloy.data.storage.models

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Ignore
import androidx.room.PrimaryKey

class BasketItemWithProduct {
    @ColumnInfo(name = "basketItem_id")
    @NonNull
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    @ColumnInfo(name = "basketItemQuantity")
    @NonNull
    var basketItemQuantity: Int = 0

    @ColumnInfo(name = "userId")
    @NonNull
    var userId: Int = 0

    @ColumnInfo(name = "productId")
    @NonNull
    var productId: Int = 0

    @Ignore
    var popularity: Int = 0

    @ColumnInfo(name = "quantity")
    var productQuantity: Int = 0

    @ColumnInfo(name = "imagesId")
    var imagesId: Int = 0

    @ColumnInfo(name = "firmId")
    var firmId: Int = 0

    @ColumnInfo(name = "firmName")
    var firmName: String = ""

    @ColumnInfo(name = "typeId")
    var typeId: Int = 0

    @ColumnInfo(name = "typeName")
    var typeName: String = ""

    @ColumnInfo(name = "model")
    var model: String = ""

    @ColumnInfo(name = "specification")
    var specification: String = ""

    @ColumnInfo(name = "isShow")
    var isShow: Boolean = false

    @ColumnInfo(name = "price")
    var price: Int = 0

    @ColumnInfo(name = "discount")
    var discount: Int = 0
}