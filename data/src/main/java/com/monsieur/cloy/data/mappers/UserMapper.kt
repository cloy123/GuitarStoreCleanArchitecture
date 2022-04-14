package com.monsieur.cloy.data.mappers

import com.monsieur.cloy.data.storage.models.UserEntity
import com.monsieur.cloy.domain.models.User

class UserMapper {
    fun toDomainModel(userEntity: UserEntity): User {
        return User(userEntity.id, userEntity.login, userEntity.password, userEntity.number)
    }

    fun toDataModel(user: User): UserEntity {
        val userEntity = UserEntity()
        userEntity.id = user.id
        userEntity.login = user.login
        userEntity.password = user.password
        userEntity.number = user.number
        return userEntity
    }
}