package com.monsieur.cloy.data.storage.models

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "productTypes")
class ProductTypeEntity {

    @ColumnInfo(name = "productType_id")
    @NonNull
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    @ColumnInfo(name = "typeName")
    @NonNull
    var typeName: String = ""
}