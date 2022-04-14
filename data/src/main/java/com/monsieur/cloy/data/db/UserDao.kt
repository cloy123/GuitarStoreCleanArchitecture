package com.monsieur.cloy.data.db

import androidx.room.*
import com.monsieur.cloy.data.storage.models.UserEntity

@Dao
interface UserDao {

    @Insert
    suspend fun insertUser(user: UserEntity): Long

    @Update
    suspend fun updateUser(user: UserEntity)

    @Query("SELECT * FROM users WHERE login =:login AND password =:password")
    suspend fun findUser(login: String, password: String): UserEntity?

    @Query("SELECT * FROM users WHERE login =:login")
    suspend fun findUserByLogin(login: String): UserEntity?

}