package com.monsieur.cloy.data.storage

import com.monsieur.cloy.data.db.UserDao
import com.monsieur.cloy.data.storage.models.UserEntity

class UserStorage(private val userDao: UserDao) {
    suspend fun insertUser(user: UserEntity): Long {
        return userDao.insertUser(user)
    }

    suspend fun updateUser(user: UserEntity) {
        userDao.updateUser(user)
    }

    suspend fun findUser(login: String, password: String): UserEntity? {
        return userDao.findUser(login, password)
    }

    suspend fun findUserByLogin(login: String): UserEntity?{
        return userDao.findUserByLogin(login)
    }
}