package com.monsieur.cloy.data.repository

import com.monsieur.cloy.data.mappers.UserMapper
import com.monsieur.cloy.data.storage.UserStorage
import com.monsieur.cloy.domain.models.User
import com.monsieur.cloy.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserRepositoryImpl(private val userStorage: UserStorage): UserRepository {

    override suspend fun insertUser(user: User): User {
        val id = userStorage.insertUser(UserMapper().toDataModel(user))
        return User(id.toInt(), user.login, user.password, user.number)
    }

    override suspend fun updateUser(user: User) {
        userStorage.updateUser(UserMapper().toDataModel(user))
    }

    override suspend fun findUser(login: String, password: String): User? {
        val userEntity = userStorage.findUser(login, password)
        return if(userEntity == null){
            null
        }else{
            UserMapper().toDomainModel(userEntity)
        }
    }

    override suspend fun isUserExist(login: String): Boolean {
        return userStorage.findUserByLogin(login) != null
    }
}