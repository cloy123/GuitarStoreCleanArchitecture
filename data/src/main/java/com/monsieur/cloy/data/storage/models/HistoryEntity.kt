package com.monsieur.cloy.data.storage.models

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "history")
class HistoryEntity {

    @ColumnInfo(name = "history_id")
    @NonNull
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    @ColumnInfo(name = "productId")
    @NonNull
    var productId: Int = 0

    @ColumnInfo(name = "quantity")
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
}