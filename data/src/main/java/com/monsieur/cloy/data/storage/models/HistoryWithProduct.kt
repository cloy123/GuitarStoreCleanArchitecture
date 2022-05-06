package com.monsieur.cloy.data.storage.models

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.time.LocalDateTime

class HistoryWithProduct {
    @ColumnInfo(name = "history_id")
    @NonNull
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    @ColumnInfo(name = "productId")
    @NonNull
    var productId: Int = 0

    @ColumnInfo(name = "historyQuantity")
    @NonNull
    var quantity: Int = 0

    @ColumnInfo(name = "price")
    @NonNull
    var price: Int = 0

    @ColumnInfo(name = "dateTime")
    @NonNull
    var dateTime: LocalDateTime = LocalDateTime.now()

    @ColumnInfo(name = "userId")
    @NonNull
    var userId: Int = 0

    @ColumnInfo(name = "address")
    @NonNull
    var address: String = ""

    @Ignore
    var popularity: Int = 0

    @ColumnInfo(name = "productQuantity")
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

    @ColumnInfo(name = "productPrice")
    var productPrice: Int = 0

    @ColumnInfo(name = "discount")
    var discount: Int = 0
}