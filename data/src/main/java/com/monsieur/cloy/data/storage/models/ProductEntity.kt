package com.monsieur.cloy.data.storage.models

import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.room.*
import com.monsieur.cloy.domain.models.Firm
import com.monsieur.cloy.domain.models.ProductType

@Entity(tableName = "products")
class ProductEntity {

    @ColumnInfo(name = "product_id")
    @NonNull
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

//    @ColumnInfo(name = "popularity")
//    @NonNull
//    var popularity: Int = 0

    @ColumnInfo(name = "quantity")
    @NonNull
    var quantity: Int = 0

    @ColumnInfo(name = "imagesId")
    @NonNull
    var imagesId: Int = 0

    @ColumnInfo(name = "firmId")
    @NonNull
    var firmId: Int = 0

    @ColumnInfo(name = "typeId")
    @NonNull
    var typeId: Int = 0

    @ColumnInfo(name = "model")
    @NonNull
    var model: String = ""

    @ColumnInfo(name = "specification")
    @NonNull
    var specification: String = ""

    @ColumnInfo(name = "isShow")
    @NonNull
    var isShow: Boolean = false

    @ColumnInfo(name = "price")
    @NonNull
    var price: Int = 0

    @ColumnInfo(name = "discount")
    @NonNull
    var discount: Int = 0
}
//INSERT INTO products(popularity, quantity, imagesId, firmId, typeId, model, specification, isShow, price, discount) VALUES(1,1,1,1,1,"qwe","qwe", "true", 100, 1)