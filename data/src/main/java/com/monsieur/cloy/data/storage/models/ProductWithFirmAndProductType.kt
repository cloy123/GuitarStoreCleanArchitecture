package com.monsieur.cloy.data.storage.models

import androidx.room.ColumnInfo

class ProductWithFirmAndProductType {
    @ColumnInfo(name = "product_id")
    var id: Int = 0

    @ColumnInfo(name = "popularity")
    var popularity: Int = 0

    @ColumnInfo(name = "quantity")
    var quantity: Int = 0

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