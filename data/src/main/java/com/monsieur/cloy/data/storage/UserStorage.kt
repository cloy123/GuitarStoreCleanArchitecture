package com.monsieur.cloy.data.storage

import com.google.common.hash.Hashing
import com.monsieur.cloy.data.db.UserDao
import com.monsieur.cloy.data.storage.models.UserEntity
import java.nio.charset.StandardCharsets

class UserStorage(private val userDao: UserDao) {
    suspend fun insertUser(user: UserEntity): Long {
        return userDao.insertUser(user)
    }

    suspend fun updateUser(user: UserEntity) {
        userDao.updateUser(user)
    }

    suspend fun findUser(login: String, password: String): UserEntity? {
        var hashedPassword = Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString()
        return userDao.findUser(login, hashedPassword)
    }

    suspend fun findUserByLogin(login: String): UserEntity?{
        return userDao.findUserByLogin(login)
    }
}