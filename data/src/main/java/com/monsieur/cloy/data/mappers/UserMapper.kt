package com.monsieur.cloy.data.mappers

import com.google.common.hash.Hashing
import com.monsieur.cloy.data.storage.models.UserEntity
import com.monsieur.cloy.domain.models.User
import java.nio.charset.StandardCharsets
import java.security.MessageDigest
import java.security.SecureRandom

class UserMapper {
    fun toDomainModel(userEntity: UserEntity): User {
        return User(userEntity.id, userEntity.login, userEntity.password, userEntity.number)
    }

    fun toDataModel(user: User): UserEntity {
        val userEntity = UserEntity()
        userEntity.id = user.id
        userEntity.login = user.login
        var password = user.password
        var hashedPassword = Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString()
        userEntity.password = hashedPassword
        userEntity.number = user.number
        return userEntity
    }
}