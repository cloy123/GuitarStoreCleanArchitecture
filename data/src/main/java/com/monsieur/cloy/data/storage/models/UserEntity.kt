package com.monsieur.cloy.data.storage.models

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "users")
class UserEntity {

    @ColumnInfo(name = "id")
    @NonNull
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    @ColumnInfo(name = "login")
    @NonNull
    var login: String = ""

    @ColumnInfo(name = "password")
    @NonNull
    var password: String = ""

    @ColumnInfo(name = "number")
    @NonNull
    var number: String = ""
}